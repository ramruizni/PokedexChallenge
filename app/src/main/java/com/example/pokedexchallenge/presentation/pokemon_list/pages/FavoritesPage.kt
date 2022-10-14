package com.example.pokedexchallenge.presentation.pokemon_list.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.presentation.destinations.PokemonDetailsScreenDestination
import com.example.pokedexchallenge.presentation.pokemon_list.PokemonListItem
import com.example.pokedexchallenge.ui.composable.box.EmptyListBox
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun FavoritesPage(
    favorites: List<Entry>,
    navigator: DestinationsNavigator
) {

    if (favorites.isEmpty()) {
        EmptyListBox(text = stringResource(R.string.pokemon_list_favorites_empty))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(favorites.size) { index ->
                val entry = favorites[index]
                PokemonListItem(
                    modifier = Modifier
                        .clickable {
                            navigator.navigate(
                                direction = PokemonDetailsScreenDestination(entry = entry),
                                onlyIfResumed = true
                            )
                        },
                    entry = entry
                )
            }
        }
    }
}