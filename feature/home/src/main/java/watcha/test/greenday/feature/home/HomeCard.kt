package watcha.test.greenday.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import watcha.test.greenday.core.designsystem.component.GreendayCard
import watcha.test.greenday.core.model.Song

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    song: Song
) {
    GreendayCard(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            HomeCardImage()
            HomeCardInfo()
        }
    }
}

@Composable
fun HomeCardImage(
    modifier: Modifier = Modifier,
) {

}

@Composable
fun HomeCardInfo(
    modifier: Modifier = Modifier,
) {

}