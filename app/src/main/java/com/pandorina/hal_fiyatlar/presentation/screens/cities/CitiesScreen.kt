package com.pandorina.hal_fiyatlar.presentation.screens.cities

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlar.R
import com.pandorina.hal_fiyatlar.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlar.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlar.presentation.component.MenuAction
import com.pandorina.hal_fiyatlar.presentation.component.MenuIcon
import com.pandorina.hal_fiyatlar.presentation.navigation.NavigationRoutes
import com.pandorina.hal_fiyatlar.presentation.screens.cities.components.CityView
import com.pandorina.hal_fiyatlar.util.InterstitialAdManager
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
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                LazyVerticalGrid(
                    state = listState,
                    cells = GridCells.Fixed(2)
                ) {
                    state.cities?.let { cities ->
                        items(cities.size ) {
                            CityView(
                                city = cities[it] ?: return@items,
                                listState = listState,
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
                        modifier = Modifier.align(CenterHorizontally),
                        color = Color.Black
                    )
                }
            }
        }
    )
}

