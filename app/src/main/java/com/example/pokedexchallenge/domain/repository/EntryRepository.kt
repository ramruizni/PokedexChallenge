package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.model.Types

interface EntryRepository {

    suspend fun fetchLocalEntryList(
        pokemonTypeList: List<Types>
    ): Resource<List<Entry>>

    suspend fun fetchRemoteEntryList(
        offset: Int,
        pokemonTypeList: List<Types>
    ): Resource<List<Entry>>

    suspend fun fetchLocalEntry(
        pokemonId: Int
    ): Resource<Entry>

    suspend fun updateLocalEntry(
        entry: Entry
    ): Resource<Entry>

    suspend fun fetchFavoritesEntryList(
    ): Resource<List<Entry>>
}