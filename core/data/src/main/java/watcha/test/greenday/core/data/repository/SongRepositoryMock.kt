package watcha.test.greenday.core.data.repository

import watcha.test.greenday.core.common.result.GreendayResult
import watcha.test.greenday.core.model.Song
import javax.inject.Inject

internal class SongRepositoryMock @Inject constructor() : SongRepository {
    override suspend fun getSongs(limit: Int, offset: Int): GreendayResult<List<Song>> {
        return GreendayResult.Success(
            listOf(
                Song(
                    trackId = 1,
                    trackName = "Track 1",
                    collectionName = "Collection 1",
                    artistName = "Artist 1",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
                ),
                Song(
                    trackId = 2,
                    trackName = "Track 2",
                    collectionName = "Collection 2",
                    artistName = "Artist 2",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
                ),
                Song(
                    trackId = 3,
                    trackName = "Track 3",
                    collectionName = "Collection 3",
                    artistName = "Artist 3",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/59/2c/5e/592c5e18-57b8-f011-1fbf-586dbb086640/859758680084_cover.png/100x100bb.jpg"
                ),
                Song(
                    trackId = 4,
                    trackName = "Track 4",
                    collectionName = "Collection 4",
                    artistName = "Artist 4",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
                ),
                Song(
                    trackId = 5,
                    trackName = "Track 5",
                    collectionName = "Collection 5",
                    artistName = "Artist 5",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
                ),
                Song(
                    trackId = 6,
                    trackName = "Track 6",
                    collectionName = "Collection 6",
                    artistName = "Artist 6",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
                ),
                Song(
                    trackId = 7,
                    trackName = "Track 7",
                    collectionName = "Collection 7",
                    artistName = "Artist 7",
                    artworkUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/dd/64/78/dd6478bc-e684-1b69-2e4e-c7e1d6a36c0a/045635953260.jpg/100x100bb.jpg"
                ),
            )
        )
    }
}