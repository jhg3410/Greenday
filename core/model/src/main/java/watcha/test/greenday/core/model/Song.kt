package watcha.test.greenday.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Song(
    val trackId: Long,
    val trackName: String,
    val collectionName: String,
    val artistName: String,
    @Json(name = "artworkUrl100") val artworkUrl: String
)