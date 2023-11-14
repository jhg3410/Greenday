package watcha.test.greenday.core.network.adapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import watcha.test.greenday.core.common.error.GreendayError.NetworkConnectionError
import watcha.test.greenday.core.common.error.GreendayError.ServerError
import watcha.test.greenday.core.common.result.GreendayResult
import java.io.IOException
import java.lang.reflect.Type

internal class ResultCallAdapter(
    private val responseType: Type
) : CallAdapter<Type, Call<GreendayResult<Type>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<Type>): Call<GreendayResult<Type>> = ResultCall(call)
}


private class ResultCall<T>(
    private val proxy: Call<T>,
) : Call<GreendayResult<T>> {

    override fun enqueue(callback: Callback<GreendayResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@ResultCall, Response.success(response.toGreendayResult()))
            }

            private fun Response<T>.toGreendayResult(): GreendayResult<T> {
                val body = body()

                return if (isSuccessful) {
                    GreendayResult.Success(body)
                } else {
                    GreendayResult.Failure(ServerError())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val failure = when (t) {
                    is IOException -> NetworkConnectionError()
                    else -> ServerError()
                }
                callback.onResponse(
                    this@ResultCall,
                    Response.success(GreendayResult.Failure(failure))
                )
            }
        })
    }


    override fun clone(): Call<GreendayResult<T>> = ResultCall(proxy.clone())

    override fun execute(): Response<GreendayResult<T>> = throw UnsupportedOperationException()

    override fun isExecuted(): Boolean = proxy.isExecuted

    override fun cancel() = proxy.cancel()

    override fun isCanceled(): Boolean = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()
}