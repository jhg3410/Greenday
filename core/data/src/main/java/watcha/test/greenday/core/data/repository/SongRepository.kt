package watcha.test.greenday.core.data.repository

import watcha.test.greenday.core.common.result.GreendayResult
import watcha.test.greenday.core.model.Song

interface SongRepository {

    suspend fun getSongs(limit: Int, offset: Int): GreendayResult<List<Song>>
}