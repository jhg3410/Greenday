package watcha.test.greenday.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.ui.state.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
    ) {
        HomeScreenTitle()
        HomeScreenContent(songs = homeViewModel.songs)
        HomeScreenState(uiState = uiState)
    }
}

@Composable
private fun HomeScreenTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(vertical = 16.dp),
        text = stringResource(id = R.string.home_title),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    songs: List<Song>
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 300.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(items = songs, key = { it.trackId }) { song ->
            HomeCard(
                modifier = Modifier.sizeIn(minWidth = 300.dp),
                song = song
            )
        }
    }
}

@Composable
private fun HomeScreenState(
    uiState: UiState<Unit>
) {
    when (uiState) {
        is UiState.Loading -> {
            Text(text = "Loading")
        }

        is UiState.Success -> {
            Unit
        }

        is UiState.Error -> {
            Text(text = "Error")
        }
    }
}