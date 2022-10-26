package com.pandorina.hal_fiyatlar.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = orange,
    primaryVariant = black,
    secondary = white
)

private val LightColorPalette = lightColors(
    primary = orange,
    primaryVariant = black,
    secondary = white
)

@Composable
fun KotlinDslExampleTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}