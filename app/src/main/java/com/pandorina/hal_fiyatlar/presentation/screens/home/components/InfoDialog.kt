package com.pandorina.hal_fiyatlar.presentation.screens.home.components

import androidx.compose.runtime.Composable
import com.pandorina.hal_fiyatlar.presentation.component.CustomDialog
import com.pandorina.hal_fiyatlar.presentation.theme.black

@Composable
fun InfoDialog(
    infoText: String,
    onConfirm: () -> Unit
) {
    CustomDialog(
        title = "Bilgi",
        text = infoText,
        confirmButtonText = "Tamam",
        confirmButtonColor = black,
        onConfirm = { onConfirm() }
    )
}