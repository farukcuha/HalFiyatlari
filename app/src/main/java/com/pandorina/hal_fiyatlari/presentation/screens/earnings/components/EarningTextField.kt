package com.pandorina.hal_fiyatlari.presentation.screens.earnings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandorina.hal_fiyatlari.presentation.theme.black

@Composable
@Preview
fun EarningTextField(
    modifier: Modifier = Modifier,
    label: String = "Ürün Adı",
    text: String = "",
    onValueChange: (String) -> Unit = {},
    onClickClear: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        isError = isError,
        trailingIcon = {
            if (text.isNotEmpty()) Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier.clickable { onClickClear() }
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = black,
            cursorColor = black,
            unfocusedLabelColor = black,
            focusedIndicatorColor = black,
                backgroundColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}