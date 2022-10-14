package com.example.pokedexchallenge.presentation.pokemon_list

sealed class PokemonListEvent {
    data class OnFirstVisibleListIndex(val index: Int) : PokemonListEvent()
    object RefreshFavorites : PokemonListEvent()
    object Logout : PokemonListEvent()
}