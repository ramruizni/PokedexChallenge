package com.example.pokedexchallenge.domain.use_case.pokemon_list

data class PokemonListUseCases(
    val fetchTypesList: FetchTypesListUseCase,
    val fetchLocalEntryList: FetchLocalEntryListUseCase,
    val fetchRemoteEntryList: FetchRemoteEntryListUseCase,
    val fetchLocalEntry: FetchLocalEntryUseCase,
    val updateLocalEntry: UpdateLocalEntryUseCase,
    val fetchFavoritesEntryList: FetchFavoritesEntryListUseCase,
)
