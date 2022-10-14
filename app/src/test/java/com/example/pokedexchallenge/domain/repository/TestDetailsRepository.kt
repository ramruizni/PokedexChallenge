package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Details


class TestDetailsRepository: DetailsRepository {
    override suspend fun fetchDetailsById(pokemonId: Int): Resource<Details> {
        val details = Details(
            pokemonId = 1,
            hp = 1,
            attack = 2,
            defense = 3,
            spAtk = 4,
            spDef = 5,
            speed = 6
        )
        return Resource.Success(details)
    }
}