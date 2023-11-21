package watcha.test.greenday.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import watcha.test.greenday.core.common.result.GreendayResult.Failure
import watcha.test.greenday.core.common.result.GreendayResult.Success
import watcha.test.greenday.core.data.repository.SongRepository
import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.ui.state.UiState
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

    init {
        getSongs()
    }

    internal fun refresh() {
        _songs.clear()
        page = FIRST_PAGE
        getSongs()
    }

    internal fun getSongs() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result = songRepository.getSongs(limit = LIMIT, offset = page * LIMIT)

            when (result) {
                is Success -> {
                    _uiState.value = UiState.Success(Unit)
                    val newSongs =
                        result.data?.filter { song -> _songs.none { it.trackId == song.trackId } }
                    _songs.addAll(newSongs ?: emptyList())
                    page++
                }

                is Failure -> {
                    _uiState.value = UiState.Error(result.error)
                }
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 0
        private const val LIMIT = 20
    }
}