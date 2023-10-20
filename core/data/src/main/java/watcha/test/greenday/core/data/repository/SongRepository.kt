package watcha.test.greenday.core.data.repository

import watcha.test.greenday.core.model.Song

interface SongRepository {

    suspend fun getSongs(
        term: String = "greenday",
        entity: String = "song",
        limit: Int = 20,
        offset: Int = 0
    ): Result<List<Song>>
}