package com.pandorina.hal_fiyatlar.presentation.screens.earnings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.Statistic

@Composable
@Preview
fun StatisticalEarningView(
    statistic: Statistic = Statistic(
        title = "Bugün",
        1455f
    )
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = statistic.title,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${statistic.income} TL",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
    }
}