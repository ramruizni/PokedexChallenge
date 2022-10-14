package com.example.pokedexchallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entry(
    var id: Int,
    var name: String,
    var url: String,
    var typeName1: String?,
    var typeName2: String?,
    var isFavorite: Boolean = false
): Parcelable