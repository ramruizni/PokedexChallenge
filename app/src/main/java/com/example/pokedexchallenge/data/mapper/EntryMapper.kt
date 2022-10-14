package com.example.pokedexchallenge.data.mapper

import com.example.pokedexchallenge.data.local.entity.EntryEntity
import com.example.pokedexchallenge.data.remote.dto.entry.EntryDto
import com.example.pokedexchallenge.domain.model.Entry

fun Entry.toEntity(): EntryEntity {
    return EntryEntity(
        id = id,
        name = name,
        url = url,
        typeName1 = typeName1,
        typeName2 = typeName2,
        isFavorite = isFavorite
    )
}

fun EntryEntity.toEntry(): Entry {
    return Entry(
        id = id,
        name = name,
        url = url,
        typeName1 = typeName1,
        typeName2 = typeName2,
        isFavorite = isFavorite
    )
}

fun EntryDto.toEntry(): Entry {
    return Entry(
        id = id,
        name = name,
        url = url,
        typeName1 = typeName1,
        typeName2 = typeName2
    )
}