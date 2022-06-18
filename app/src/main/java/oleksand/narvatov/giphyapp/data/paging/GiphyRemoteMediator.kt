package oleksand.narvatov.giphyapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemoteKeyDao
import oleksand.narvatov.giphyapp.data.remote.helper.GiphyApiHelper
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.model.local.RemoteKey
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GiphyRemoteMediator @Inject constructor(
    private val giphyDao: GiphyDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val pagingApiHelper: GiphyApiHelper,
) : RemoteMediator<Int, Giphy>() {
    //TODO REALLY TEMP REMOVE IT
    public var query: String = ""

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Giphy>
    ) = with(state) {
        val page = when(val loadTypeResult = handleLoadType(loadType)) {
            is LoadTypeResult.PageResult -> {
                loadTypeResult.page
            }
            is LoadTypeResult.MediatorSuccessResult -> {
                return@with loadTypeResult.success
            }
        }

        runCatchingMediator {
            val serverData = loadDataFromRemote(page, limit)

            serverData.saveToDatabase(page)
        }
    }

    private suspend fun PagingState<Int, Giphy>.handleLoadType(loadType: LoadType) = when (loadType) {
        LoadType.REFRESH -> {
            val remoteKeys = getRemoteKeyByItem(closestItemToPosition())
            val page = remoteKeys?.nextKey?.prevKey ?: INITIAL_PAGE

            PageResult(page)
        }
        LoadType.APPEND -> {
            val remoteKey = getRemoteKeyByItem(lastItemOrNull())
            getLoadTypeResult(remoteKey, remoteKey?.nextKey)
        }
        LoadType.PREPEND -> {
            val remoteKey = getRemoteKeyByItem(firstItemOrNull())
            getLoadTypeResult(remoteKey, remoteKey?.prevKey)
        }
    }

    private suspend fun getRemoteKeyByItem(item: Giphy?) =
        item?.id?.let { id -> remoteKeyDao.get(id) }

    private suspend fun loadDataFromRemote(
        page: Int,
        limit: Int,
    ): List<Giphy> {
        val offset = getOffset(page, limit)

        return pagingApiHelper.searchGifs(query, offset, limit)
    }

    private suspend fun List<Giphy>.saveToDatabase(
        page: Int,
    ): MediatorResult.Success {
        val endOfPagingReached = calculateEndOfPagination()

        val prevKey = page.prevKey()
        val nextKey = page.nextKey(endOfPagingReached)
        val remoteKeys = map { RemoteKey(it.id, nextKey ,prevKey) }

        giphyDao.insert(this)
        remoteKeyDao.insert(remoteKeys)

        return MediatorResult.Success(endOfPaginationReached = endOfPagingReached)
    }

    companion object {
        internal const val INITIAL_PAGE = 0
    }

}