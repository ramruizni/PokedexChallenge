package com.example.pokedexchallenge.domain.use_case.pokemon_details

import com.example.pokedexchallenge.domain.use_case.pokemon_list.FetchLocalEntryUseCase
import com.example.pokedexchallenge.domain.use_case.pokemon_list.UpdateLocalEntryUseCase

data class PokemonDetailsUseCases(
    val fetchDetailsById: FetchDetailsByIdUseCase,
    val fetchLocalEntry: FetchLocalEntryUseCase,
    val updateLocalEntry: UpdateLocalEntryUseCase
)
