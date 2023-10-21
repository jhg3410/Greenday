package watcha.test.greenday.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import watcha.test.greenday.core.data.repository.SongRepository
import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.ui.state.UiState
import watcha.test.greenday.core.ui.state.createUiStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    private val _songs = mutableStateListOf<Song>()
    val songs: List<Song> = _songs

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSongs()
        }
    }

    suspend fun getSongs() {
        createUiStateFlow {
            songRepository.getSongs()
        }.collect { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    _uiState.value = uiState
                }

                is UiState.Error -> {
                    _uiState.value = uiState
                }

                is UiState.Success -> {
                    _uiState.value = UiState.Success(Unit)
                    _songs.addAll(uiState.data)
                }
            }
        }
    }
}