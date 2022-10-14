package com.example.pokedexchallenge.data.remote.dto.types

data class TypesResponseDto(
    val name: String,
    val pokemon: List<TypesResponsePokemonDto>
)