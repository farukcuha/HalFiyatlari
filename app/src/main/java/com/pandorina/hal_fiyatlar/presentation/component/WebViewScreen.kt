package com.pandorina.hal_fiyatlar.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

var webViewTitle: String = ""
var webViewUrl: String = ""

@Composable
fun WebViewScreen(
    navController: NavController = rememberNavController()
) {
    var isLoading: Boolean by remember {
        mutableStateOf(true)
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = webViewTitle,
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
                    url = webViewUrl,
                    onBack = { navController.popBackStack() },
                    isLoading = {
                        isLoading = it
                    }
                )
            }
        }
    )
}