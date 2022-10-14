package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.model.Types


class TestEntryRepository : EntryRepository {

    private val fakeEntry = Entry(
        id = 1,
        name = "Bulbasaur",
        url = "fake_url",
        typeName1 = "Grass",
        typeName2 = "Poison"
    )

    override suspend fun fetchLocalEntryList(pokemonTypeList: List<Types>): Resource<List<Entry>> {
        return Resource.Success(listOf(fakeEntry, fakeEntry))
    }

    override suspend fun fetchRemoteEntryList(
        offset: Int,
        pokemonTypeList: List<Types>
    ): Resource<List<Entry>> {
        return Resource.Success(listOf(fakeEntry, fakeEntry))
    }

    override suspend fun fetchLocalEntry(pokemonId: Int): Resource<Entry> {
        return Resource.Success(fakeEntry)
    }

    override suspend fun updateLocalEntry(entry: Entry): Resource<Entry> {
        return Resource.Success(fakeEntry)
    }

    override suspend fun fetchFavoritesEntryList(): Resource<List<Entry>> {
        return Resource.Success(listOf(fakeEntry, fakeEntry))
    }

}