package oleksand.narvatov.giphyapp.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import oleksand.narvatov.giphyapp.data.repository.base.GiphyRepository
import oleksand.narvatov.giphyapp.domain.SearchPagingGifsUseCase
import oleksand.narvatov.giphyapp.model.local.Giphy
import oleksand.narvatov.giphyapp.ui.viewmodel.base.PagingViewModel
import javax.inject.Inject

@HiltViewModel
class GiphyListViewModel @Inject constructor(
    private val searchPagingGifsUseCase: SearchPagingGifsUseCase,
    private val giphyRepository: GiphyRepository,
): PagingViewModel<Giphy>() {

    fun searchGifs(query: String = "") {
        viewModelScope.launch {
            searchPagingGifsUseCase(query).collectPaging()
        }
    }

    fun removeGif(giphy: Giphy) {
        viewModelScope.launch {
            giphyRepository.removeGif(giphy)
        }
    }

}