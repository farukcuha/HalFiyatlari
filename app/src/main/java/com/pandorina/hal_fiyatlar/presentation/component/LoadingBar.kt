package com.pandorina.hal_fiyatlar.presentation.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pandorina.hal_fiyatlar.presentation.theme.black

@Composable
fun LoadingBar(
    modifier: Modifier = Modifier
){
    CircularProgressIndicator(
        color = black,
        modifier = modifier
    )
}