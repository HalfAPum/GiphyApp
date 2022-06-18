package oleksand.narvatov.giphyapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import okio.IOException
import oleksand.narvatov.giphyapp.model.local.RemoteKey
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
internal suspend fun runCatchingMediator(
    block: suspend () -> RemoteMediator.MediatorResult
): RemoteMediator.MediatorResult {
    return try {
        block.invoke()
    } catch (exception: IOException) {
        RemoteMediator.MediatorResult.Error(exception)
    } catch (exception: HttpException) {
        RemoteMediator.MediatorResult.Error(exception)
    }
}

internal fun List<Any>.calculateEndOfPagination(): Boolean {
    return isNullOrEmpty()
}

internal fun <Result : Any> PagingState<Int, Result>.closestItemToPosition(): Result? {
    return anchorPosition?.let { anchorPosition ->
        closestItemToPosition(anchorPosition)
    }
}

internal fun Int.prevKey(): Int? {
    return if (this == GiphyRemoteMediator.INITIAL_PAGE) null else prevKey
}

internal fun Int.nextKey(isEndOfPagination: Boolean): Int? {
    return if (isEndOfPagination) null else nextKey
}

internal val Int.nextKey: Int
    get() = this + 1

internal val Int.prevKey: Int
    get() = this - 1

internal val PagingState<Int, *>.limit: Int
    get() = config.pageSize

internal fun PageResult(page: Int) = LoadTypeResult.PageResult(page)

@OptIn(ExperimentalPagingApi::class)
internal fun MediatorSuccessResult(
    endOfPaginationReached: Boolean
) = LoadTypeResult.MediatorSuccessResult(
    RemoteMediator.MediatorResult.Success(endOfPaginationReached)
)

internal fun getLoadTypeResult(remoteKey: RemoteKey?, key: Int?): LoadTypeResult {
    return key?.let {
        PageResult(key)
    } ?: MediatorSuccessResult(remoteKey != null)
}

internal fun getOffset(page: Int, limit: Int) =
    (page - GiphyRemoteMediator.INITIAL_PAGE) * limit