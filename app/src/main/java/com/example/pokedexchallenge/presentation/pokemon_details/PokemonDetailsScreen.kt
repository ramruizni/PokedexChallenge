package com.example.pokedexchallenge.presentation.pokemon_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Constants
import com.example.pokedexchallenge.commons.formatAsEntryNumber
import com.example.pokedexchallenge.commons.getTypeColor
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.ui.composable.TypeItem
import com.example.pokedexchallenge.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun PokemonDetailsScreen(
    entry: Entry,
    navigator: DestinationsNavigator,
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = entry.name.replaceFirstChar { it.uppercase() },
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(PokemonDetailsEvent.TogglePokemonAsFavorite)
                    }) {
                        Icon(
                            painter = painterResource(
                                id =
                                if (state.entry?.isFavorite == true) R.drawable.ic_favorite_marked
                                else R.drawable.ic_favorite_unmarked
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(getTypeColor(entry.typeName1))
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball_white),
                modifier = Modifier
                    .size(400.dp)
                    .offset(x = 80.dp, y = (-80).dp)
                    .align(Alignment.TopEnd),
                contentDescription = null,
                alpha = 0.1f
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GlideImage(
                    modifier = Modifier.weight(1f),
                    imageModel = "${Constants.SPRITE_URL}${entry.id}${Constants.SPRITE_EXT}",
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        TypeItem(typeName = entry.typeName1, isLarge = true)
                        Spacer(modifier = Modifier.width(6.dp))
                        TypeItem(typeName = entry.typeName2, isLarge = true)
                    }
                    Text(
                        text = entry.id.toString().formatAsEntryNumber(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    state.details?.let { details ->

                        val maxStat = listOf(
                            details.hp,
                            details.attack,
                            details.defense,
                            details.spAtk,
                            details.spDef,
                            details.speed
                        ).maxOrNull()!!

                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_hp),
                            value = details.hp,
                            maxValue = maxStat,
                            color = HpColor
                        )
                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_atk),
                            value = details.attack,
                            maxValue = maxStat,
                            color = AtkColor
                        )
                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_def),
                            value = details.defense,
                            maxValue = maxStat,
                            color = DefColor
                        )
                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_spatk),
                            value = details.spAtk,
                            maxValue = maxStat,
                            color = SpAtkColor
                        )
                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_spdef),
                            value = details.spDef,
                            maxValue = maxStat,
                            color = SpDefColor
                        )
                        PokemonStatIndicator(
                            name = stringResource(R.string.pokemon_details_speed),
                            value = details.speed,
                            maxValue = maxStat,
                            color = SpeedColor
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun PokemonStatIndicator(
    name: String,
    value: Int,
    maxValue: Int,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 6.dp, start = 26.dp, end = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.width(80.dp),
            style = MaterialTheme.typography.bodyMedium
                .copy(color = MaterialTheme.colorScheme.primary)
        )
        Text(
            text = value.toString(),
            modifier = Modifier.width(46.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
                .copy(color = MaterialTheme.colorScheme.primary)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .border(
                    width = 0.2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            val barWeight = value.toFloat()
            val remainingWeight =
                if (maxValue != value) maxValue.toFloat() - value.toFloat() else 0.1f
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(barWeight)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = color)
            )
            Box(
                modifier = Modifier
                    .weight(remainingWeight)
            )
        }
    }
}