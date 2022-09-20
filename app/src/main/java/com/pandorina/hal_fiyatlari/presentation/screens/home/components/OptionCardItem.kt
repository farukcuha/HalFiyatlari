package com.pandorina.hal_fiyatlari.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pandorina.hal_fiyatlari.presentation.screens.home.HomeOptions
import com.pandorina.hal_fiyatlari.presentation.theme.black

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OptionCardItem(
    modifier: Modifier,
    option: HomeOptions,
    resourceIconId: Int? = null,
    infoText: String? = null,
    navigate: () -> Unit,
    isActive: Boolean = true
) {
    var showInfoDialog by remember {
        mutableStateOf(false)
    }

    if (showInfoDialog && infoText != null) InfoDialog(infoText = infoText) {
        showInfoDialog = false
    }

    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 16.dp,
        onClick = {
            if (isActive) navigate()
            else showInfoDialog = true
        }
    ) {
        Box {
            Text(
                text = stringResource(id = option.title),
                color = black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 8.dp, top = 12.dp, end = 64.dp)
                    .align(Alignment.TopStart),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(id = option.image),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .offset(8.dp, 8.dp)
                    .align(Alignment.BottomEnd)
            )
            resourceIconId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .alpha(0.4f)
                        .padding(horizontal = 12.dp)
                        .offset(y = 8.dp)
                        .size(64.dp)
                )
            }
        }
    }
}