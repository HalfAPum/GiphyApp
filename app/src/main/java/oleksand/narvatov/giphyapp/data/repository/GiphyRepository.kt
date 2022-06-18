package oleksand.narvatov.giphyapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.local.dao.RemovedGiphyDao
import oleksand.narvatov.giphyapp.data.local.helper.GetRemovedGifsIdsDaoHelper
import oleksand.narvatov.giphyapp.data.local.helper.RemoveGifDaoHelper
import oleksand.narvatov.giphyapp.data.paging.GiphyRemoteMediator
import oleksand.narvatov.giphyapp.data.repository.base.AbsRepository
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.model.local.RemovedGiphy
import timber.log.Timber
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalPagingApi::class)
class GiphyRepository @Inject constructor(
    private val giphyRemoteMediator: GiphyRemoteMediator,
    private val giphyDao: GiphyDao,
    private val getRemovedGifsIdsDaoHelper: GetRemovedGifsIdsDaoHelper,
    private val removeGifDaoHelper: RemoveGifDaoHelper,
) : AbsRepository() {

    fun searchGifs(
        query: String,
        config: PagingConfig,
    ) = Pager(
        config = config,
        remoteMediator = giphyRemoteMediator.apply { this.query = query },
        pagingSourceFactory = { giphyDao.getPagingSource() },
    ).flow

    suspend fun clearKeys() = withContext(dispatchers.IO) {
        giphyDao.clear()
    }

    suspend fun getRemovedGifsIds() = withContext(dispatchers.IO) {
        getRemovedGifsIdsDaoHelper()
    }

    suspend fun removeGif(giphy: Giphy) = withContext(dispatchers.IO) {
        removeGifDaoHelper(giphy)
    }

}