package com.example.pokedexchallenge.presentation.pokemon_list

import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.model.Types

data class PokemonListState(
    val types: List<Types> = emptyList(),
    val entries: List<Entry> = emptyList(),
    val favoriteEntries: List<Entry> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val page: Int = 0
)
