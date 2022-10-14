package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.data.local.dao.DetailsDao
import com.example.pokedexchallenge.data.mapper.toDetails
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.data.remote.api.DetailsApi
import com.example.pokedexchallenge.domain.model.Details
import com.example.pokedexchallenge.domain.repository.DetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsRepositoryImpl @Inject constructor(
    private val api: DetailsApi,
    private val dao: DetailsDao
) : DetailsRepository {

    override suspend fun fetchDetailsById(pokemonId: Int): Resource<Details> {
        val detailsFromCache = dao.findByPokemonId(pokemonId)
        
        if (detailsFromCache != null) {
            return Resource.Success(detailsFromCache.toDetails())
        }

        val response = api.getDetails(pokemonId)
        val detailsFromRemote = response.toDetails()
        dao.insert(detailsFromRemote.toEntity())
        return Resource.Success(detailsFromRemote)
    }
}