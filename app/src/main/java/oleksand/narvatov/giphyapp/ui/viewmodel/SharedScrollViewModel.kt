package oleksand.narvatov.giphyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedScrollViewModel @Inject constructor() : ViewModel() {

    private val _scrollPositionStateFlow = MutableStateFlow(0)
    val scrollPositionStateFlow = _scrollPositionStateFlow.asStateFlow()

    fun saveScroll(position: Int) {
        viewModelScope.launch {
            _scrollPositionStateFlow.emit(position)
        }
    }

    fun resetScroll() {
        viewModelScope.launch {
            _scrollPositionStateFlow.emit(0)
        }
    }

}