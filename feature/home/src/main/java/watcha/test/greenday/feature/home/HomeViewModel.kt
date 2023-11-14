package watcha.test.greenday.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var page = FIRST_PAGE

    suspend fun refresh() {
        _songs.clear()
        page = FIRST_PAGE
        getSongs()
    }

    suspend fun getSongs() {
        createUiStateFlow {
            songRepository.getSongs(limit = LIMIT, offset = page * LIMIT)
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
                    val newSongs =
                        uiState.data?.filter { song -> _songs.none { it.trackId == song.trackId } }
                    _songs.addAll(newSongs ?: emptyList())
                    page++
                }
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 0
        private const val LIMIT = 20
    }
}