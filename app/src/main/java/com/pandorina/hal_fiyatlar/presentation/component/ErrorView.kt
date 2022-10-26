package com.pandorina.hal_fiyatlar.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandorina.hal_fiyatlar.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Preview
@Composable
fun CustomLottieView(
    modifier: Modifier = Modifier,
    lottieRaw: Int = R.raw.lottie_error,
    message: String = "Something went wrong!"
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRaw))
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .width(240.dp)
            .wrapContentHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = message,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

