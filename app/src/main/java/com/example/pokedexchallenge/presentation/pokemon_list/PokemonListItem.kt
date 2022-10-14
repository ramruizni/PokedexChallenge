package com.example.pokedexchallenge.presentation.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Constants.SPRITE_EXT
import com.example.pokedexchallenge.commons.Constants.SPRITE_URL
import com.example.pokedexchallenge.commons.formatAsEntryNumber
import com.example.pokedexchallenge.commons.getTypeColor
import com.example.pokedexchallenge.domain.model.Entry
import com.example.pokedexchallenge.ui.composable.TypeItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    entry: Entry
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.41f)
            .clip(RoundedCornerShape(22.dp))
            .background(getTypeColor(entry.typeName1))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
        ) {
            Text(
                text = entry.id.toString().formatAsEntryNumber(),
                modifier = Modifier
                    .padding(end = 18.dp, top = 12.dp)
                    .align(Alignment.TopEnd),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color(0x1A000000)
                )
            )
            Image(
                painter = painterResource(id = R.drawable.pokeball_white_small),
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = 10.dp, y = 10.dp)
                    .align(Alignment.BottomEnd),
                contentDescription = null,
                alpha = 0.2f
            )
            GlideImage(
                modifier = Modifier
                    .width(110.dp)
                    .height(100.dp)
                    .align(Alignment.BottomEnd),
                imageModel = "$SPRITE_URL${entry.id}$SPRITE_EXT",
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            )
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = entry.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    TypeItem(typeName = entry.typeName1)
                    Spacer(modifier = Modifier.height(6.dp))
                    TypeItem(typeName = entry.typeName2)
                }
            }
        }
    }

}