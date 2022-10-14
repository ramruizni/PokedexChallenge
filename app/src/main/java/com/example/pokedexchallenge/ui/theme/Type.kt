package com.example.pokedexchallenge.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pokedexchallenge.R

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 45.sp,
        color = Color.White
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 22.77.sp,
        color = Color.White
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        color = Color.White
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        color = Color.White
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 30.sp,
        color = Color.White
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 22.77.sp,
        color = Color.White
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circular_std_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 40.sp,
        color = Color.Black
    )
)