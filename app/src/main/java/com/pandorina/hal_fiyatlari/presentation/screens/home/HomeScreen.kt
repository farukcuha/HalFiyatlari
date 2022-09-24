package com.pandorina.hal_fiyatlari.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.presentation.component.*
import com.pandorina.hal_fiyatlari.presentation.navigation.NavigationRoutes
import com.pandorina.hal_fiyatlari.presentation.screens.home.components.CityPhoto
import com.pandorina.hal_fiyatlari.presentation.screens.home.components.InfoDialog
import com.pandorina.hal_fiyatlari.presentation.screens.home.components.OptionCardItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.photoUiState.value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.home),
                actions = {
                    MenuIcon(action = MenuAction.Share) {
                        Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.pandorina.hal_fiyatlari")
                            context.startActivity(Intent.createChooser(this, "Uygulamayı Paylaş!"))
                        }
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(6f)
                        .fillMaxSize()
                ) {
                    CityPhoto(uiState.photo)
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Black
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(4f)
                        .padding(8.dp)
                ) {
                    val modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                    Row(
                        modifier = modifier
                    ) {
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.Prices,
                            navigate = {
                                navController.navigate(NavigationRoutes.Cities.route)
                            },
                            infoText = uiState.infoText,
                            isActive = uiState.isPricesActive
                        )
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.Earnings,
                            navigate = {
                                navController.navigate(NavigationRoutes.Earnings.route)
                            },
                            infoText = uiState.infoText,
                            isActive = uiState.isEarningsActive
                        )
                    }
                    Row(
                        modifier = modifier
                    ) {
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.News,
                            navigate = {
                                navController.navigate(NavigationRoutes.News.route)
                            },
                            infoText = uiState.infoText,
                            isActive = uiState.isNewsActive
                        )
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.Currency,
                            navigate = {
                                navController.navigate(NavigationRoutes.Currency.route)
                            },
                            infoText = uiState.infoText,
                            isActive = uiState.isCurrencyActive
                        )
                    }
                }
            }
            if (uiState.showDialog) InfoDialog(infoText = uiState.infoText) {
                viewModel._photoUiState.value = uiState.copy(showDialog = false)
            }
        }
    )
}



