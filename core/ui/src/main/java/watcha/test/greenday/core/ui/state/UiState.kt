package watcha.test.greenday.core.ui.state

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import watcha.test.greenday.core.common.error.GreendayError
import watcha.test.greenday.core.common.result.GreendayResult
import watcha.test.greenday.core.common.result.fold

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T?) : UiState<T>
    data class Error(val type: GreendayError) : UiState<Nothing>
}

private fun <T> GreendayResult<T>.toUiState(): UiState<T> {
    return fold(
        onSuccess = { UiState.Success(it) },
        onFailure = { UiState.Error(it) }
    )
}

fun <T> createUiStateFlow(
    loadData: suspend () -> GreendayResult<T>,
) = flow {
    emit(loadData().toUiState())
}.onStart { emit(UiState.Loading) }