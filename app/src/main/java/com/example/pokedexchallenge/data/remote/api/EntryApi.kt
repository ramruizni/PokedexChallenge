package com.example.pokedexchallenge.data.remote.api

import com.example.pokedexchallenge.data.remote.dto.entry.EntryResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EntryApi {

    @GET("pokemon")
    suspend fun getEntryList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): EntryResponseDto
}