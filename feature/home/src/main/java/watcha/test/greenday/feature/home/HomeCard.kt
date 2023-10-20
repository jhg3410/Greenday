package watcha.test.greenday.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import watcha.test.greenday.core.designsystem.component.GreendayCard
import watcha.test.greenday.core.model.Song

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    song: Song
) {
    GreendayCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeCardImage(
                modifier = Modifier,
                artworkUrl = song.artworkUrl
            )
            HomeCardInfo(
                trackName = song.trackName,
                collectionName = song.collectionName,
                artistName = song.artistName
            )
        }
    }
}

@Composable
fun HomeCardImage(
    modifier: Modifier = Modifier,
    artworkUrl: String
) {
    GreendayCard(
        modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        AsyncImage(
            modifier = modifier.size(100.dp),
            model = artworkUrl,
            contentDescription = "artwork Image"
        )
    }
}

@Composable
fun HomeCardInfo(
    modifier: Modifier = Modifier,
    trackName: String,
    collectionName: String,
    artistName: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = trackName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = collectionName,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = artistName,
            style = MaterialTheme.typography.bodySmall
        )
    }
}