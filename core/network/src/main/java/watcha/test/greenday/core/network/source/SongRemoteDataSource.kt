package watcha.test.greenday.core.network.source

import watcha.test.greenday.core.model.Song

interface SongRemoteDataSource {
    suspend fun getSongs(
        limit: Int,
        offset: Int
    ): Result<List<Song>>
}