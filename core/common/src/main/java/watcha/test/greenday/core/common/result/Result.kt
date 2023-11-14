package watcha.test.greenday.core.common.result

import watcha.test.greenday.core.common.error.GreendayError

sealed interface GreendayResult<out T> {
    class Success<T>(val data: T?) : GreendayResult<T>
    class Failure(val error: GreendayError) : GreendayResult<Nothing>
}


fun <R, T> GreendayResult<T>.fold(
    onSuccess: (value: T?) -> R,
    onFailure: (error: GreendayError) -> R
): R {
    return when (this) {
        is GreendayResult.Success -> onSuccess(data)
        is GreendayResult.Failure -> onFailure(error)
    }
}


fun <R, T> GreendayResult<T>.map(
    transform: (value: T?) -> R
): GreendayResult<R> {
    return when (this) {
        is GreendayResult.Success -> GreendayResult.Success(transform(data))
        is GreendayResult.Failure -> GreendayResult.Failure(error)
    }
}