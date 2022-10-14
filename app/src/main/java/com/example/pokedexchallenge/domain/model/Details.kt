package com.example.pokedexchallenge.domain.model

data class Details(
    var pokemonId: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int
)