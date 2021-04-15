package com.kevin.twtoilet.api

import com.kevin.twtoilet.Data.toiletData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface openDataService {

    @GET("fac_p_28?")
    fun fetchToiletsList(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("api_key") api_key: String = "3a7bca5a-56ff-4193-a498-2e40878e92e6"
    ): Call<toiletData>
}