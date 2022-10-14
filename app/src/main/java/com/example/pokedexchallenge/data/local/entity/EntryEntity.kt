package com.example.pokedexchallenge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntryEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var url: String,
    var typeName1: String?,
    var typeName2: String?,
    var isFavorite: Boolean = false
)