package com.example.pokedexchallenge.data.remote.api

import com.example.pokedexchallenge.data.remote.dto.types.TypesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TypesApi {

    @GET("type/{id}")
    suspend fun getTypesList(
        @Path("id")
        id: Int
    ): TypesResponseDto
}