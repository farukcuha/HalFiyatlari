package com.pandorina.hal_fiyatlari.presentation.screens.cities.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.presentation.component.CoilImage
import com.pandorina.hal_fiyatlari.presentation.theme.city_foreground
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityView(
    city: City,
    listState: LazyListState,
    onClickFavoriteButton: (City) -> Unit,
    navigate: (City) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        shape = RoundedCornerShape(16.dp),
        onClick = {
            navigate(city)
        },
        modifier = Modifier
            .height(120.dp)
            .padding(4.dp)
    ) {
        Box {
            CoilImage(imageUrl = city.imageUrl)
            Box(
                modifier = Modifier
                    .background(color = city_foreground)
                    .fillMaxSize()
            )
            Text(
                text = "${city.title}",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            FavoriteButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                isFavorite = city.isFavorite,
                onClick = {
                    onClickFavoriteButton(city)
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                }
            )
        }
    }
}