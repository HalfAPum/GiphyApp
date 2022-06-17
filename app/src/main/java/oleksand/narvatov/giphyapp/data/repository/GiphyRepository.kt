package oleksand.narvatov.giphyapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.scopes.ViewModelScoped
import oleksand.narvatov.giphyapp.data.local.dao.GiphyDao
import oleksand.narvatov.giphyapp.data.paging.GiphyRemoteMediator
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalPagingApi::class)
class GiphyRepository @Inject constructor(
    private val giphyRemoteMediator: GiphyRemoteMediator,
    private val giphyDao: GiphyDao,
) {

    fun searchGifs(
        query: String = "a"
    ) = Pager(
        config = PagingConfig(30),
        remoteMediator = giphyRemoteMediator.apply { this.query = query },
        pagingSourceFactory = { giphyDao.getPagingSource() },
    ).flow
}