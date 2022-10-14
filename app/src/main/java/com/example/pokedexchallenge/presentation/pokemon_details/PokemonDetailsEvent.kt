package com.example.pokedexchallenge.presentation.pokemon_details

sealed class PokemonDetailsEvent {
    object TogglePokemonAsFavorite: PokemonDetailsEvent()
}