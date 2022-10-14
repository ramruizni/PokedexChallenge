package com.example.pokedexchallenge.commons

import androidx.compose.ui.graphics.Color

fun getTypeColor(type: String?): Color {
    return when (type) {
        "normal" -> Color(0xFFA8A878)
        "fire" -> Color(0xFFFB6C6C)
        "water" -> Color(0xFF77BDFE)
        "grass" -> Color(0xFF48D0B0)
        "electric" -> Color(0xFFFBCD66)
        "ice" -> Color(0xFF98D8D8)
        "fighting" -> Color(0xFFC13128)
        "poison" -> Color(0xFF883488)
        "ground" -> Color(0xFFE1C068)
        "flying" -> Color(0xFFA791F0)
        "psychic" -> Color(0xFFF85888)
        "bug" -> Color(0xFFC8D088)
        "rock" -> Color(0xFFB8A038)
        "ghost" -> Color(0xFF705898)
        "dark" -> Color(0xFF6F5848)
        "dragon" -> Color(0xFF7038F8)
        "steel" -> Color(0xFFB8B7D0)
        "fairy" -> Color(0xFFF0B6BC)
        else -> Color(0xFFA8A878)
    }
}