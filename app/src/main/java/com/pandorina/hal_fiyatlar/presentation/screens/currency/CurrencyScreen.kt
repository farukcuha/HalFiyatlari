package com.pandorina.hal_fiyatlar.presentation.screens.currency

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import com.pandorina.hal_fiyatlar.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlar.presentation.component.*
import com.pandorina.hal_fiyatlar.presentation.screens.currency.components.CurrencyItem

@Composable
fun CurrencyScreen(
    navController: NavController
) {
    val viewModel: CurrencyViewModel = hiltViewModel()
    val uiState = viewModel.uiState.value

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
                if (uiState.isLoading){
                    LoadingBar(modifier = Modifier.align(Alignment.Center))
                }
                uiState.currencies?.let { currencies ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ){
                        items(currencies.size){
                            CurrencyItem(currencies[it])
                        }
                    }
                }
            }
        }
    )
}