package com.pandorina.hal_fiyatlari.presentation.screens.cities.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(
    icon: ImageVector
) {
    Icon(icon,
        contentDescription = "",
        tint = Color.White,
        modifier = Modifier.size(24.dp))
}