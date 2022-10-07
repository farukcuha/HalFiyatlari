package com.pandorina.hal_fiyatlari.presentation.screens.cities

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.presentation.component.CoilImage
import com.pandorina.hal_fiyatlari.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlari.presentation.component.MenuAction
import com.pandorina.hal_fiyatlari.presentation.component.MenuIcon
import com.pandorina.hal_fiyatlari.presentation.navigation.NavigationRoutes
import com.pandorina.hal_fiyatlari.presentation.screens.cities.components.CityView
import com.pandorina.hal_fiyatlari.presentation.theme.city_foreground
import com.pandorina.hal_fiyatlari.util.InterstitialAdManager
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesScreen(
    navController: NavController
) {
    val viewModel: CitiesViewModel = hiltViewModel()
    val state = viewModel.uiState.value
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.cities),
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController.popBackStack()
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                LazyColumn(
                    state = listState
                ) {
                    state.cities?.let { cities ->
                        items(cities.size, key = { cities[it]?.id ?: "" } ) {
                            CityView(
                                city = cities[it] ?: return@items,
                                listState = listState,
                                modifier = Modifier
                                    .animateItemPlacement(
                                        animationSpec = tween(500)),
                            onClickFavoriteButton = { city ->
                                if (city.isFavorite) viewModel.deleteFavoriteCity(city.id ?: return@CityView)
                                else viewModel.insertFavoriteCity(
                                    CityEntity(
                                        id = city.id!!,
                                        title = city.title,
                                        imageUrl = city.imageUrl,
                                        isFavorite = true
                                    )
                                )
                            }) { city ->
                                navController.navigate(
                                    "${NavigationRoutes.Prices.route}/${city.id}/${city.title}"
                                )
                                InterstitialAdManager.show()
                            }
                        }
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black
                    )
                }
            }
        }
    )
}

