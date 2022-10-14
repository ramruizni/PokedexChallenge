package com.example.pokedexchallenge.presentation.pokemon_details

import com.example.pokedexchallenge.domain.model.Details
import com.example.pokedexchallenge.domain.model.Entry

data class PokemonDetailsState(
    val entry: Entry? = null,
    val details: Details? = null,
    val isLoading: Boolean = false
)