package watcha.test.greenday.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import watcha.test.greenday.core.model.Song

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
    ) {
        HomeScreenContent()
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 300.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        items(items = MockData.songList) { song ->
            HomeCard(
                modifier = Modifier.sizeIn(minWidth = 300.dp),
                song = song
            )
        }
    }
}


private object MockData {
    val songList = listOf(
        Song(
            trackName = "Track 1",
            collectionName = "Collection 1",
            artistName = "Artist 1",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 2",
            collectionName = "Collection 2",
            artistName = "Artist 2",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 3",
            collectionName = "Collection 3",
            artistName = "Artist 3",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 4",
            collectionName = "Collection 4",
            artistName = "Artist 4",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 5",
            collectionName = "Collection 5",
            artistName = "Artist 5",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 6",
            collectionName = "Collection 6",
            artistName = "Artist 6",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
        ),
        Song(
            trackName = "Track 7",
            collectionName = "Collection 7",
            artistName = "Artist 7",
            artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
        ),
    )
}