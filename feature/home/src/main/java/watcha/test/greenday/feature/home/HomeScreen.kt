package watcha.test.greenday.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.ui.state.UiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                homeViewModel.refresh()
            }
        }
    )

    LaunchedEffect(uiState) {
        if (uiState !is UiState.Loading) {
            isRefreshing = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
    ) {
        HomeScreenTitle()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.TopCenter
        ) {
            HomeScreenContent(songs = homeViewModel.songs)
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
        HomeScreenState(modifier = Modifier, uiState = uiState, retry = homeViewModel::getSongs)
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
        modifier = modifier,
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
    modifier: Modifier = Modifier,
    uiState: UiState<Unit>,
    retry: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                Unit
            }

            is UiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = uiState.throwable.message ?: "Unknown Error",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Button(onClick = {
                        coroutineScope.launch {
                            retry()
                        }
                    }) {
                        Text(text = "Retry", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}