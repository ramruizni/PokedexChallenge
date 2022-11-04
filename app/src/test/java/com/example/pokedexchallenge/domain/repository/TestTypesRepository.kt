package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Types


class TestTypesRepository : TypesRepository {
    override suspend fun fetchTypesList(): Resource<List<Types>> {
        val types1 = Types(
            pokemonId = 1,
            nameSlot1 = "Poison",
            nameSlot2 = "Dark"
        )
        val types2 = Types(
            pokemonId = 2,
            nameSlot1 = "Ice",
            nameSlot2 = "Normal"
        )
        return Resource.Success(listOf(types1, types2))
    }

}