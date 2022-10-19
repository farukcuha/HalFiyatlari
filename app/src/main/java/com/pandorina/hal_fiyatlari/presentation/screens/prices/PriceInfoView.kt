package com.pandorina.hal_fiyatlari.presentation.screens.prices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandorina.hal_fiyatlari.presentation.theme.white

@Composable
@Preview
fun PriceInfoView() {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(white)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.align(Center)
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "high_price",
                    tint = Color.Red
                )
                Text("En Düşük Fiyat", modifier = Modifier.align(CenterVertically))
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = Modifier.align(Center)
            ) {
                Icon(
                    Icons.Default.ArrowDropUp,
                    contentDescription = "high_price",
                    tint = Color.Green
                )
                Text("En Yüksek Fiyat",
                    modifier = Modifier.align(CenterVertically)
                )
            }
        }
    }
}