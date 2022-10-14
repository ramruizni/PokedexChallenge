package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Types


class TestTypesRepository : TypesRepository {
    override suspend fun fetchTypesList(): Resource<List<Types>> {
        val types = Types(
            pokemonId = 1,
            nameSlot1 = "Poison",
            nameSlot2 = "Dark"
        )
        return Resource.Success(listOf(types, types))
    }

}