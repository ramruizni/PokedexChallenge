package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Types

interface TypesRepository {

    suspend fun fetchTypesList(): Resource<List<Types>>
}