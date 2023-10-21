package watcha.test.greenday.core.network.source

import watcha.test.greenday.core.model.Song
import watcha.test.greenday.core.network.api.ItunesApi
import javax.inject.Inject

internal class SongRemoteDataSourceImpl @Inject constructor(
    private val itunesApi: ItunesApi
) : SongRemoteDataSource {
    override suspend fun getSongs(limit: Int, offset: Int): Result<List<Song>> {
        return itunesApi.getSongs(limit = limit, offset = offset).map { songResponse ->
            songResponse.results
        }
    }
}