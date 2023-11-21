package watcha.test.greenday.core.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import watcha.test.greenday.core.common.result.GreendayResult
import watcha.test.greenday.core.network.model.SongResponse

internal interface ItunesApi {
    @GET("search")
    suspend fun getSongs(
        @Query("term") term: String = "greenday",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GreendayResult<SongResponse>
}