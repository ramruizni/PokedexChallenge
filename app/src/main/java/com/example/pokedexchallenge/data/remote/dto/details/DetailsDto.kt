package com.example.pokedexchallenge.data.remote.dto.details

data class DetailsDto(
    var pokemonId: Int,
    val stats: List<StatDto>
)