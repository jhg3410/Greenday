package watcha.test.greenday.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import watcha.test.greenday.core.data.repository.SongRepository
import watcha.test.greenday.core.data.repository.SongRepositoryMock

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindSongRepository(
        repository: SongRepositoryMock
    ): SongRepository
}