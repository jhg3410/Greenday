package watcha.test.greenday.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import watcha.test.greenday.core.network.adapter.ResultCallAdapterFactory
import watcha.test.greenday.core.network.api.ItunesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val ITUNES_BASE_URL = "https://itunes.apple.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideItunesApi(okHttpClient: OkHttpClient): ItunesApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ITUNES_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
            .create(ItunesApi::class.java)
    }
}