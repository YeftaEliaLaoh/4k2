package com.example.a4k.core.network.services

import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Representation interface of the API endpoints.
 */
interface Service {

    @GET(".")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): BaseResponse<CharacterResponse>
}
