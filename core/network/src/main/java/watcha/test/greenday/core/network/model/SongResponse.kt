package watcha.test.greenday.core.network.model

import com.squareup.moshi.JsonClass
import watcha.test.greenday.core.model.Song

@JsonClass(generateAdapter = true)
data class SongResponse(
    val resultCount: Int,
    val results: List<Song>
)
