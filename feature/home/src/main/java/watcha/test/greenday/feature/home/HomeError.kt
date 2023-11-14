package watcha.test.greenday.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import watcha.test.greenday.core.common.error.GreendayError.NetworkConnectionError
import watcha.test.greenday.core.common.error.GreendayError.ServerError
import watcha.test.greenday.core.designsystem.error.NetworkErrorScreen
import watcha.test.greenday.core.designsystem.error.ServerErrorScreen
import watcha.test.greenday.core.ui.state.UiState

@Composable
internal fun HomeScreenErrorWhenNoItems(
    modifier: Modifier = Modifier,
    error: UiState.Error,
    retry: () -> Unit
) {
    when (error.type) {
        is ServerError -> {
            val serverError = (error.type as ServerError)
            ServerErrorScreen(
                modifier = modifier.fillMaxSize(),
                message = serverError.message,
                subMessage = serverError.subMessage
            )
        }

        is NetworkConnectionError -> {
            val networkError = (error.type as NetworkConnectionError)
            NetworkErrorScreen(
                modifier = modifier.fillMaxSize(),
                message = networkError.message,
                retry = retry
            )
        }
    }
}


@Composable
internal fun HomeScreenErrorWhenHasItems(
    modifier: Modifier = Modifier,
    error: UiState.Error,
    retry: () -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val message = when (error.type) {
            is ServerError -> {
                (error.type as ServerError).message
            }

            is NetworkConnectionError -> {
                (error.type as NetworkConnectionError).message
            }
        }
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        if (error.type is NetworkConnectionError) {
            Button(onClick = { retry() }) {
                Text(text = "Retry", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}