package com.example.pokedexchallenge.data.remote.dto.entry

data class EntryResponseDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<EntryDto>
)