package oleksand.narvatov.giphyapp.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import oleksand.narvatov.giphyapp.data.repository.GiphyRepository
import javax.inject.Inject

@HiltViewModel
class GiphyListViewModel @Inject constructor(
    private val giphyRepository: GiphyRepository,
): ViewModel() {

    fun searchGifs() = giphyRepository.searchGifs()

}