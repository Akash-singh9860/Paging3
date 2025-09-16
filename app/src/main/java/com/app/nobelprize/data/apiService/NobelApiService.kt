package com.app.nobelprize.data.apiService

import com.app.nobelprize.data.model.NobelPrizeResponse
import retrofit2.http.GET
import retrofit2.http.Query
interface NobelApiService {
    @GET("2.1/nobelPrizes")
    suspend fun getNobelPrizes(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): NobelPrizeResponse
}
