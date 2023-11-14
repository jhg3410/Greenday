package watcha.test.greenday.core.designsystem.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import watcha.test.greenday.core.designsystem.R

@Composable
internal fun BaseErrorScreen(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorCharacter()
        MainMessage(message = message)
    }
}

@Composable
private fun ErrorCharacter(
    modifier: Modifier = Modifier
) {
    val copyRight = "navid semi"

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
        )
        Text(
            text = copyRight,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun MainMessage(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        modifier = modifier,
        text = message,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge
    )
}