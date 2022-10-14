package com.example.pokedexchallenge.data.remote.api

import com.example.pokedexchallenge.data.remote.dto.details.DetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("pokemon/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ): DetailsDto
}