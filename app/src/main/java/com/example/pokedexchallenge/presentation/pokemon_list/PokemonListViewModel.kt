package com.example.pokedexchallenge.presentation.pokemon_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.use_case.pokemon_list.PokemonListUseCases
import com.example.pokedexchallenge.testability.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val useCases: PokemonListUseCases,
    // private val preferences: SharedPreferences
) : ViewModel() {

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        fetchTypesList()
    }

    fun onEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.OnFirstVisibleListIndex -> {
                val lastIndex = state.entries.last().id
                if (lastIndex == 904) return
                if (!state.isRefreshing && event.index == lastIndex - 10) {
                    state = state.copy(isRefreshing = true)
                    fetchEntryList(offset = lastIndex / 20)
                }
            }
            is PokemonListEvent.RefreshFavorites -> {
                if (state.types.isNotEmpty()) {
                    fetchFavoritesEntryList()
                }
            }
            is PokemonListEvent.Logout -> {
                /*viewModelScope.launch {
                    preferences.edit().putBoolean(SHARED_PREF_IS_LOGGED_IN, false).apply()
                }*/
                // TODO: Toast toast_logout_successful
            }
        }
    }

    private fun fetchTypesList() {
        viewModelScope.launch(dispatchers.main) {
            useCases
                .fetchTypesList()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { types ->
                                state = state.copy(types = types)
                                fetchEntryList()
                                fetchFavoritesEntryList()
                            }
                        }
                        is Resource.Error -> {
                            // TODO: Toast error_unknown
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun fetchEntryList(offset: Int = 0) {
        viewModelScope.launch(dispatchers.main) {
            if (offset == 0) {
                useCases
                    .fetchLocalEntryList(state.types)
                    .collect { result -> collectEntryListResult(result) }
            } else {
                useCases
                    .fetchRemoteEntryList(offset, state.types)
                    .collect { result -> collectEntryListResult(result) }
            }
        }
    }

    private fun collectEntryListResult(result: Resource<List<Entry>>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { entries ->
                    state = state.copy(entries = entries, isRefreshing = false)
                }
            }
            is Resource.Error -> {
                // TODO: Toast error_unknown
            }
            is Resource.Loading -> {
                state = state.copy(isRefreshing = result.isLoading)
            }
        }
    }

    private fun fetchFavoritesEntryList() {
        viewModelScope.launch(dispatchers.main) {
            useCases
                .fetchFavoritesEntryList()
                .collect { result -> collectFavoritesEntryListResult(result) }
        }
    }

    private fun collectFavoritesEntryListResult(result: Resource<List<Entry>>) {
        when (result) {
            is Resource.Success -> {
                result.data?.let { favorites ->
                    state = state.copy(favoriteEntries = favorites)
                }
            }
            is Resource.Error -> {
                // TODO: Toast error_unknown
            }
            is Resource.Loading -> {
                state = state.copy(isRefreshing = result.isLoading)
            }
        }
    }
}