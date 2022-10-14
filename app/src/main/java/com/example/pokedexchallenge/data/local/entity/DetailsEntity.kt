package com.example.pokedexchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailsEntity(
    @PrimaryKey(autoGenerate = false)
    var pokemonId: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int
)