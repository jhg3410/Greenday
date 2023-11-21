package watcha.test.greenday.core.designsystem.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ServerErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    subMessage: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseErrorScreen(message = message)
        SubMessage(subMessage = subMessage)
    }
}

@Composable
private fun SubMessage(
    subMessage: String
) {
    Text(
        text = subMessage,
        style = MaterialTheme.typography.bodySmall
    )
}