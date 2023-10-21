package watcha.test.greenday.core.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import watcha.test.greenday.core.network.BuildConfig


internal val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}