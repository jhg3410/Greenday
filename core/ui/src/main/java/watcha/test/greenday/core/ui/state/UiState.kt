package watcha.test.greenday.core.ui.state

import watcha.test.greenday.core.common.error.GreendayError

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T?) : UiState<T>
    data class Error(val type: GreendayError) : UiState<Nothing>
}