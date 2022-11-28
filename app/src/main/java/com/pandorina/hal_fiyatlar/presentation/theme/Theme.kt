package com.pandorina.hal_fiyatlar.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun KotlinDslExampleTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}