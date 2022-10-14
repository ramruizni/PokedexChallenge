package com.example.pokedexchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypesEntity(
    @PrimaryKey(autoGenerate = false)
    var pokemonId: Int,
    var nameSlot1: String?,
    var nameSlot2: String?
)