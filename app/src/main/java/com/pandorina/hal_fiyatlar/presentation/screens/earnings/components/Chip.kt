package com.pandorina.hal_fiyatlar.presentation.screens.earnings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandorina.hal_fiyatlar.presentation.theme.gray


@Composable
@Preview
fun ChipGroup(
    modifier: Modifier = Modifier,
    labels: List<String> = listOf("Patlıcan", "Elma", "Salatalık"),
    onClickClear: () -> Unit = {},
    onClickLabel: (String) -> Unit = {},
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = modifier.weight(8.5f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(labels.size){
                Chip(labels[it]){ label ->
                    onClickLabel(label)
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
                .clickable { onClickClear() },
            text = "Temizle")
    }
}

@Composable
@Preview
fun Chip(
    label: String = "Patlıcan",
    onClick: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .border(0.5.dp, gray, RoundedCornerShape(16.dp))
    ) {
        Text(
            text = label,
            modifier = Modifier
                .clickable {
                    onClick(label)
                }
                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp))
    }
}