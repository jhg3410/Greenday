package watcha.test.greenday.core.designsystem.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NetworkErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseErrorScreen(message = message)
        Spacer(modifier = Modifier.height(30.dp))
        RetryButton {
            retry()
        }
    }
}


@Composable
private fun RetryButton(
    retry: () -> Unit
) {
    Button(onClick = { retry() }) {
        Text(text = "Retry", color = MaterialTheme.colorScheme.onPrimary)
    }
}
