package com.example.pokedexchallenge.data.remote.dto.entry

data class EntryDto(
    var id: Int,
    var name: String,
    var url: String,
    var typeName1: String?,
    var typeName2: String?
)