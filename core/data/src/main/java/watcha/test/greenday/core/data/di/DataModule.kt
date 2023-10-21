package watcha.test.greenday.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import watcha.test.greenday.core.data.repository.SongRepository
import watcha.test.greenday.core.data.repository.SongRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindSongRepository(
        repository: SongRepositoryImpl
    ): SongRepository
}