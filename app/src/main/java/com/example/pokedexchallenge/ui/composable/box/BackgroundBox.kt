package com.example.pokedexchallenge.ui.composable.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexchallenge.R

@Composable
fun BoxBackground(
    padding: PaddingValues = PaddingValues(),
    inverted: Boolean = false
) {
    val isLight = MaterialTheme.colorScheme.background.luminance() > 0.5
    val resourceId = if (isLight) R.drawable.pokeball_black else R.drawable.pokeball_white

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Image(
            painter = painterResource(id = resourceId),
            modifier = Modifier
                .size(400.dp)
                .offset(x = 40.dp, y = (-40).dp)
                .align(if (inverted) Alignment.BottomStart else Alignment.TopEnd),
            contentDescription = null,
            alpha = 0.1f
        )
    }
}