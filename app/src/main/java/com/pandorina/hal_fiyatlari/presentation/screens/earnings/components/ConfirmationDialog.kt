package com.pandorina.hal_fiyatlari.presentation.screens.earnings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.white

@Composable
fun ConfirmationDialog(
    title: String = "",
    text: String = "",
    confirmButtonText: String = "",
    confirmButtonColor: Color = black,
    onConfirm: () -> Unit = {},
    dismissButtonText: String = "",
    dismissButtonColor: Color = black,
    onDismiss: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = confirmButtonColor,
                    contentColor = white
                ),
                onClick = {
                    onConfirm()
                }) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = dismissButtonColor,
                    contentColor = white
                ),
                onClick = {
                    onDismiss()
                }) {
                Text(dismissButtonText, color = white)
            }
        }
    )
}