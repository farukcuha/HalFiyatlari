package com.pandorina.hal_fiyatlari.presentation.screens.currency.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pandorina.hal_fiyatlari.domain.model.currency.Currency
import com.pandorina.hal_fiyatlari.presentation.theme.tradeGreen
import com.pandorina.hal_fiyatlari.presentation.theme.tradeRed

@Composable
@Preview
fun CurrencyItem(
    currency: Currency = Currency(
        name = "Gram Altın",
        price = "1.002,485",
        changeRate = "0.99%",
        changeAmount = "(9,87)",
        trendUp = false
    )
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 8.dp
    ) {
        ConstraintLayout {
            val (refName, refPrice) = createRefs()
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = "${currency.name}",
                modifier = Modifier.constrainAs(refName){
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(refPrice.top)
                    bottom.linkTo(refPrice.bottom)
                    end.linkTo(refPrice.start, 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            Column(
                modifier = Modifier.constrainAs(refPrice){
                    end.linkTo(parent.end, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }
            ){
                Text(
                    text = "${currency.price}",
                    modifier = Modifier.align(End),
                    fontSize = 18.sp
                )
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = if (currency.trendUp == true) tradeGreen else tradeRed,
                        fontSize = 16.sp
                    )){
                        append(if (currency.trendUp == true) "⬆︎" else "⬇︎")
                        append("${currency.changeRate}")
                    }
                    append("  ")
                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp
                        )
                    ){
                        append("${currency.changeAmount}")
                    }
                })
            }
        }
    }
}