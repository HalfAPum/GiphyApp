package oleksand.narvatov.giphyapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.paging.GiphyRemoteMediator
import oleksand.narvatov.giphyapp.data.repository.base.AbsRepository
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalPagingApi::class)
class GiphyRepository @Inject constructor(
    private val giphyRemoteMediator: GiphyRemoteMediator,
    private val giphyDao: GiphyDao,
) : AbsRepository() {

    fun searchGifs(
        query: String,
        pagingConfig: PagingConfig,
    ) = Pager(
        config = pagingConfig,
        remoteMediator = giphyRemoteMediator.apply { this.query = query },
        pagingSourceFactory = { giphyDao.getPagingSource() },
    ).flow

    suspend fun clearKeys() = withContext(dispatchers.IO) {
        giphyDao.clear()
    }

}