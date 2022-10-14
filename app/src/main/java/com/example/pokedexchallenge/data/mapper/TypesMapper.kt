package com.example.pokedexchallenge.data.mapper

import com.example.pokedexchallenge.data.local.entity.TypesEntity
import com.example.pokedexchallenge.data.remote.dto.types.TypesDto
import com.example.pokedexchallenge.domain.model.Types

fun Types.toEntity(): TypesEntity {
    return TypesEntity(
        pokemonId = pokemonId,
        nameSlot1 = nameSlot1,
        nameSlot2 = nameSlot2
    )
}

fun TypesEntity.toTypes(): Types {
    return Types(
        pokemonId = pokemonId,
        nameSlot1 = nameSlot1,
        nameSlot2 = nameSlot2
    )
}

fun TypesDto.toTypes(): Types {
    return Types(
        pokemonId = pokemonId,
        nameSlot1 = nameSlot1,
        nameSlot2 = nameSlot2
    )
}