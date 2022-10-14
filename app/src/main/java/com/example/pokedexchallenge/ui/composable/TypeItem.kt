package com.example.pokedexchallenge.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypeItem(typeName: String?, isLarge: Boolean? = false) {
    if (typeName != null)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color(0x4DFFFFFF))
        ) {
            Text(
                text = typeName.replaceFirstChar { it.uppercase() },
                style = if (isLarge == true)
                    MaterialTheme.typography.labelLarge
                else MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(
                        horizontal = if (isLarge == true) 18.dp else 14.dp,
                        vertical = if (isLarge == true) 8.dp else 5.dp
                    )
            )
        }
}