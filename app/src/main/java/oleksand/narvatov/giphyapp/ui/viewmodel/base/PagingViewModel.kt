package oleksand.narvatov.giphyapp.ui.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.cachedIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import oleksand.narvatov.giphyapp.utils.Dispatchers
import oleksand.narvatov.giphyapp.utils.MutablePagingFlow
import oleksand.narvatov.giphyapp.utils.errorHandler
import javax.inject.Inject

abstract class PagingViewModel<T : Any> : ViewModel() {

    @Inject
    lateinit var dispatchers: Dispatchers

    private val _pagingData by MutablePagingFlow<T>()
    val pagingData get() = _pagingData.asSharedFlow()

    /**
     * Default job for paging.
     */
    private var pagingJob: Job? = null

    /**
     * Transform paging [Flow] to [pagingData].
     */
    fun Flow<PagingData<T>>.collectPaging() {
        cachedIn(viewModelScope)
            .onEach(_pagingData::emit)
            .flowOn(errorHandler + dispatchers.IO)
            .launchIn(viewModelScope)
            .assignJob()
    }

    /**
     * Use this function to avoid multiple [RemoteMediator] instances at the same time.
     */
    private fun Job.assignJob() {
        pagingJob?.cancel()
        pagingJob = this
    }

}