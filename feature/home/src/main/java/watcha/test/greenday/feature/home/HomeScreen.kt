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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import watcha.test.greenday.core.common.error.GreendayError.*
import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.ui.pagination.Pageable
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
        HomeScreenContent(
            songs = homeViewModel.songs,
            uiState = uiState,
            getSongs = homeViewModel::getSongs,
            refresh = homeViewModel::refresh
        )
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
    songs: List<Song>,
    uiState: UiState<Unit>,
    getSongs: () -> Unit,
    refresh: () -> Unit
) {
    if (songs.isNotEmpty()) {
        HomeScreenContentWithItems(
            songs = songs,
            uiState = uiState,
            getSongs = getSongs,
            refresh = refresh
        )
    } else {
        HomeScreenContentWithOutItems(
            uiState = uiState,
            getSongs = getSongs
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenContentWithItems(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    uiState: UiState<Unit>,
    getSongs: () -> Unit,
    refresh: () -> Unit
) {

    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            refresh()
        }
    )

    LaunchedEffect(uiState) {
        if (uiState !is UiState.Loading) {
            isRefreshing = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter
    ) {

        val lazyGridState = rememberLazyGridState().apply {
            Pageable(onLoadMore = getSongs, itemCountProvider = songs::size)
        }

        LazyVerticalGrid(
            modifier = modifier,
            state = lazyGridState,
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

            val stateItem: (content: @Composable () -> Unit) -> Unit = { content ->
                item(span = { GridItemSpan(maxLineSpan) }) {
                    content()
                }
            }

            when (uiState) {
                is UiState.Success -> Unit

                is UiState.Loading -> {
                    stateItem {
                        HomeScreenLoadingWithItems()
                    }
                }

                is UiState.Error -> {
                    stateItem {
                        HomeScreenErrorWithItems(
                            error = uiState,
                            retry = getSongs
                        )
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            contentColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun HomeScreenContentWithOutItems(
    uiState: UiState<Unit>,
    getSongs: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is UiState.Loading -> {
                HomeScreenLoadingWithoutItems()
            }

            is UiState.Error -> {
                HomeScreenErrorWithoutItems(
                    error = uiState,
                    retry = getSongs
                )
            }

            is UiState.Success -> {
                HomeScreenEmpty()
            }
        }
    }
}