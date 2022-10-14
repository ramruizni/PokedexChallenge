package com.example.pokedexchallenge.presentation.pokemon_details

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.domain.use_case.pokemon_details.PokemonDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val app: Application,
    private val useCases: PokemonDetailsUseCases,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(PokemonDetailsState())
        private set

    init {
        state = state.copy(entry = stateHandle.get<Entry>("entry"))
        fetchEntry()
        fetchPokemonDetails()
    }

    fun onEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.TogglePokemonAsFavorite -> {
                togglePokemonAsFavorite()
            }
        }
    }

    private fun fetchEntry() {
        viewModelScope.launch {
            useCases
                .fetchLocalEntry(pokemonId = state.entry!!.id)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { entry ->
                                state = state.copy(entry = entry)
                            }
                        }
                        else -> Unit
                    }
                }
        }
    }

    private fun fetchPokemonDetails() {
        viewModelScope.launch {
            useCases.fetchDetailsById(state.entry!!.id)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { details ->
                                state = state.copy(details = details)
                            }
                        }
                        is Resource.Error -> {
                            Toasty.error(
                                app,
                                app.getString(R.string.error_unknown),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun togglePokemonAsFavorite() {
        viewModelScope.launch {
            val entry = state.entry!!
            val updated = entry.copy(isFavorite = !entry.isFavorite)
            useCases.updateLocalEntry(updated)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { updatedEntry ->
                                state = state.copy(entry = updatedEntry)
                                val change = if (updatedEntry.isFavorite) "Marked" else "Unmarked"
                                Toasty.info(
                                    app,
                                    "$change as Favorite",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        else -> Unit
                    }
                }
        }
    }

}