package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Details

interface DetailsRepository {

    suspend fun fetchDetailsById(
        pokemonId: Int
    ): Resource<Details>
}