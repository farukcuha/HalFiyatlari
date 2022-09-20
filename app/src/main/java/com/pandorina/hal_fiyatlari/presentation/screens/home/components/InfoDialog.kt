package com.pandorina.hal_fiyatlari.presentation.screens.home.components

import androidx.compose.runtime.Composable
import com.pandorina.hal_fiyatlari.presentation.component.CustomDialog
import com.pandorina.hal_fiyatlari.presentation.theme.black

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