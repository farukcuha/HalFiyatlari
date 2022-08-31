package com.pandorina.hal_fiyatlari.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlari.presentation.component.MenuAction
import com.pandorina.hal_fiyatlari.presentation.component.MenuIcon
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pandorina.hal_fiyatlari.domain.model.photo.Photo
import com.pandorina.hal_fiyatlari.presentation.component.CoilImage
import com.pandorina.hal_fiyatlari.presentation.navigation.NavGraph
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.orange
import org.koin.androidx.compose.inject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.photoUiState.value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.home),
                actions = {
                    MenuIcon(action = MenuAction.Settings) {
                        Log.d("action", "clicked_settings")
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
                                navController.navigate(NavGraph.Cities.route)
                            }
                        )
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.Earnings,
                            navigate = {
                                navController.navigate(NavGraph.Earnings.route)
                            }
                        )
                    }
                    Row(
                        modifier = modifier
                    ) {
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.News,
                            resourceIconId = R.drawable.ic_sondakika,
                            navigate = {
                                navController.navigate(NavGraph.News.route)
                            }
                        )
                        OptionCardItem(
                            modifier = modifier,
                            option = HomeOptions.Currency,
                            resourceIconId = R.drawable.ic_doviz,
                            navigate = {
                                navController.navigate(NavGraph.Currency.route)
                            }
                        )
                    }
                }
            }
        }
    )
}

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OptionCardItem(
    modifier: Modifier,
    option: HomeOptions,
    resourceIconId: Int? = null,
    navigate: () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 16.dp,
        onClick = navigate
    ) {
        Box {
            Text(
                text = stringResource(id = option.title),
                color = black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 8.dp, top = 12.dp, end = 64.dp)
                    .align(Alignment.TopStart),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(id = option.image),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .offset(8.dp, 8.dp)
                    .align(Alignment.BottomEnd)
            )
            resourceIconId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .alpha(0.4f)
                        .padding(horizontal = 12.dp)
                        .offset(y = 8.dp)
                        .size(64.dp)
                )
            }
        }
    }
}