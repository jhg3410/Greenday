package watcha.test.greenday.core.ui.state

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val throwable: Throwable) : UiState<Nothing>
}

private fun <T> Result<T>.toUiState(): UiState<T> {
    return fold(
        onSuccess = {
            UiState.Success(it)
        },
        onFailure = {
            UiState.Error(it)
        }
    )
}

fun <T> createUiStateFlow(
    loadData: suspend () -> Result<T>,
) = flow {
    emit(loadData().toUiState())
}.onStart { emit(UiState.Loading) }