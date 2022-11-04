package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.commons.getPokemonIdFromUrl
import com.example.pokedexchallenge.data.local.dao.EntryDao
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.data.mapper.toEntry
import com.example.pokedexchallenge.data.remote.api.EntryApi
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.model.Types
import com.example.pokedexchallenge.domain.repository.EntryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntryRepositoryImpl @Inject constructor(
    private val api: EntryApi,
    private val dao: EntryDao
) : EntryRepository {

    override suspend fun fetchLocalEntryList(
        pokemonTypeList: List<Types>
    ): Resource<List<Entry>> {
        val listFromCache = dao.findAll()
        if (listFromCache.isNotEmpty()) {
            return Resource.Success(listFromCache.map { it.toEntry() })
        }

        val response = api.getEntryList(20, 0)
        val listFromRemote = response.results.map {
            val pokemonId = it.url.getPokemonIdFromUrl()
            val pokemonTypes = pokemonTypeList[pokemonId - 1]
            Entry(
                pokemonId,
                it.name,
                it.url,
                pokemonTypes.nameSlot1,
                pokemonTypes.nameSlot2
            )
        }

        dao.insertAll(listFromRemote.map { it.toEntity() })
        val newListFromCache = dao.findAll()
        return Resource.Success(newListFromCache.map { it.toEntry() })
    }

    override suspend fun fetchRemoteEntryList(
        offset: Int,
        pokemonTypeList: List<Types>
    ): Resource<List<Entry>> {

        // this solves a bug for pokemon forms having ids over 10000
        // to stop the count at 904
        val limit = if (offset == 45) 4 else 20

        val response = api.getEntryList(limit, offset * 20)
        val listFromRemote = response.results.map {
            val pokemonId = it.url.getPokemonIdFromUrl()
            val pokemonTypes = pokemonTypeList[pokemonId - 1]
            Entry(
                pokemonId,
                it.name,
                it.url,
                pokemonTypes.nameSlot1,
                pokemonTypes.nameSlot2
            )
        }

        dao.insertAll(listFromRemote.map { it.toEntity() })
        val newListFromCache = dao.findAll()
        return Resource.Success(newListFromCache.map { it.toEntry() })
    }


    override suspend fun fetchLocalEntry(pokemonId: Int): Resource<Entry> {
        val entryFromCache = dao.find(pokemonId = pokemonId)
        return Resource.Success(entryFromCache.toEntry())
    }

    override suspend fun updateLocalEntry(entry: Entry): Resource<Entry> {
        dao.update(entry.toEntity())
        val entryFromCache = dao.find(entry.id)
        return Resource.Success(entryFromCache.toEntry())
    }

    override suspend fun fetchFavoritesEntryList(): Resource<List<Entry>> {
        val listFromCache = dao.findAllFavorites()
        return Resource.Success(listFromCache.map { it.toEntry() })
    }

}