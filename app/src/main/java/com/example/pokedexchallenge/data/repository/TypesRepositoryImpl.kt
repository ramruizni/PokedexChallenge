package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.commons.getPokemonIdFromUrl
import com.example.pokedexchallenge.data.local.dao.TypesDao
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.data.mapper.toTypes
import com.example.pokedexchallenge.data.remote.api.TypesApi
import com.example.pokedexchallenge.data.remote.dto.types.TypesResponseDto
import com.example.pokedexchallenge.domain.model.Types
import com.example.pokedexchallenge.domain.repository.TypesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypesRepositoryImpl @Inject constructor(
    private val api: TypesApi,
    private val dao: TypesDao
) : TypesRepository {

    override suspend fun fetchTypesList(): Resource<List<Types>> {
        val listFromCache = dao.findAll()
        if (listFromCache.isNotEmpty()) {
            return Resource.Success(
                listFromCache.map { it.toTypes() }
            )
        }

        // TODO: IMPROVE TIME WITH DEFERRED
        val types = mutableMapOf<Int, Types>()
        val responses = mutableListOf<TypesResponseDto>()
        for (typeId in 1..18) {
            val response = api.getTypesList(typeId)
            responses.add(response)
        }

        for (response in responses) {
            for (pokemonType in response.pokemon) {
                val key = pokemonType.pokemon.url.getPokemonIdFromUrl()
                if (!types.containsKey(key)) {
                    types[key] = Types(key, null, null)
                }
                if (pokemonType.slot == 1) {
                    types[key]?.nameSlot1 = response.name
                }
                if (pokemonType.slot == 2) {
                    types[key]?.nameSlot2 = response.name
                }
            }

        }

        val listFromRemote = mutableListOf<Types>()
        for (type in types) {
            listFromRemote.add(type.value)
        }

        listFromRemote.sortBy { it.pokemonId }
        dao.insertAll(listFromRemote.map { it.toEntity() })
        return Resource.Success(listFromRemote)
    }

}