package com.pandorina.hal_fiyatlari.presentation.screens.currency

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlari.R
import com.pandorina.hal_fiyatlari.presentation.component.*

@Composable
fun CurrencyScreen(
    navController: NavController
) {
    var isLoading: Boolean by remember {
        mutableStateOf(true)
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.currency),
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
                if (isLoading){
                    LoadingBar(modifier = Modifier.align(Alignment.Center))
                }
                CustomWebView(
                    url = "https://kur.doviz.com/",
                    onBack = { navController.popBackStack() },
                    isLoading = {
                        isLoading = it
                    }
                )
            }
        }
    )
}