package watcha.test.greenday.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import watcha.test.greenday.core.network.source.SongRemoteDataSource
import watcha.test.greenday.core.network.source.SongRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface SourceModule {

    @Binds
    fun bindSongRemoteDataSource(
        dataSource: SongRemoteDataSourceImpl
    ): SongRemoteDataSource
}