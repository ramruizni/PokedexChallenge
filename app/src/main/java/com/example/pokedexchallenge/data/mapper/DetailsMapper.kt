package com.example.pokedexchallenge.data.mapper

import com.example.pokedexchallenge.data.local.entity.DetailsEntity
import com.example.pokedexchallenge.data.remote.dto.details.DetailsDto
import com.example.pokedexchallenge.domain.model.Details

fun Details.toEntity(): DetailsEntity {
    return DetailsEntity(
        pokemonId = pokemonId,
        hp = hp,
        attack = attack,
        defense = defense,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed
    )
}

fun DetailsEntity.toDetails(): Details {
    return Details(
        pokemonId = pokemonId,
        hp = hp,
        attack = attack,
        defense = defense,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed
    )
}

fun DetailsDto.toDetails(): Details {
    var hp = 0
    var attack = 0
    var defense = 0
    var spAtk = 0
    var spDef = 0
    var speed = 0
    for (statDto in stats) {
        val name = statDto.stat.name
        when (name) {
            "hp" -> {
                hp = statDto.base_stat
            }
            "attack" -> {
                attack = statDto.base_stat
            }
            "defense" -> {
                defense = statDto.base_stat
            }
            "special-attack" -> {
                spAtk = statDto.base_stat
            }
            "special-defense" -> {
                spDef = statDto.base_stat
            }
            "speed" -> {
                speed = statDto.base_stat
            }
            else -> Unit
        }
    }
    return Details(
        pokemonId = pokemonId,
        hp = hp,
        attack = attack,
        defense = defense,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed
    )
}
