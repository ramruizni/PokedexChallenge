package com.example.pokedexchallenge.commons

fun String.getPokemonIdFromUrl(): Int {
    var numberIndex = 0
    var i = this.length - 2
    while (this[i] != '/') {
        numberIndex = i
        i--
    }
    return Integer.valueOf(this.substring(numberIndex, this.length - 1))
}

fun String.formatAsEntryNumber(): String {
    return when (this.length) {
        1 -> "#00$this"
        2 -> "#0$this"
        else -> "#$this"
    }
}