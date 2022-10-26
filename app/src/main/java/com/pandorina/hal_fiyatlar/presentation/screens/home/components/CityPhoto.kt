package com.pandorina.hal_fiyatlar.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pandorina.hal_fiyatlar.domain.model.photo.Photo
import com.pandorina.hal_fiyatlar.presentation.component.CoilImage

@Composable
fun CityPhoto(
    cityPhoto: Photo?,
) {
    Box {
        CoilImage(imageUrl = cityPhoto?.imageUrl)
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 600f
                    )
                )
                .fillMaxSize()
        )
        Text(
            text = cityPhoto?.title ?: "",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}