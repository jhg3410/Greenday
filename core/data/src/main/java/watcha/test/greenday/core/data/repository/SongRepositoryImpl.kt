package watcha.test.greenday.core.data.repository

import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.network.source.SongRemoteDataSource
import javax.inject.Inject

internal class SongRepositoryImpl @Inject constructor(
    private val songRemoteDataSource: SongRemoteDataSource
) : SongRepository {
    override suspend fun getSongs(limit: Int, offset: Int): Result<List<Song>> {
        return songRemoteDataSource.getSongs(limit = limit, offset = offset)
    }
}