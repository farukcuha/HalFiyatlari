package com.pandorina.hal_fiyatlari.presentation.screens.prices

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pandorina.hal_fiyatlari.domain.model.price.Price
import com.pandorina.hal_fiyatlari.presentation.component.*
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.white
import kotlin.math.max

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PricesScreen(
    navController: NavController? = null,
) {
    val viewModel: PricesViewModel = hiltViewModel()
    val uiState = viewModel.uiState.value
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "${viewModel.title.value} Hal Fiyatları",
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController?.popBackStack()
                    }
                }
            )
        },
        content = {
            Column {
                CurrentDate(dates = uiState.dates){
                    viewModel.getPrices(viewModel.cityId.value, it)
                }
                uiState.prices?.let { prices ->
                    LazyColumn {
                        items(prices.size) {
                            PriceItem(prices[it])
                        }
                    }
                }
                if (uiState.isLoading) {
                    LoadingBar(modifier = Modifier.align(CenterHorizontally))
                }
            }
        }
    )
}

@Composable
fun CurrentDate(
    dates: List<String?>?,
    onClickDate: (String) -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(0.5.dp, black, RoundedCornerShape(8.dp))
            .clickable { expanded = true }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${dates?.getOrNull(selectedIndex)}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Center)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().heightIn(max = 360.dp)
        ) {
            dates?.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        expanded = false
                        onClickDate("${dates[index]}")
                    },
                ) {
                    Text(
                        text = "${dates[index]}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun PriceItem(
    price: Price?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(white)
            .padding(horizontal = 8.dp)
    ) {
        val (refIcon, refInfo, refPrice, refDivider) = createRefs()

        if(price?.icon?.isNotEmpty() == true) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .constrainAs(refIcon) {
                        start.linkTo(parent.start, 8.dp)
                        top.linkTo(parent.top, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    }
                    .clip(CircleShape)
            ) {
                CoilImage(imageUrl = price?.icon)
            }
        }
        Column(
            modifier = Modifier.constrainAs(refInfo) {
                if(price?.icon?.isNotEmpty() == true) start.linkTo(refIcon.end, 8.dp)
                else start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                end.linkTo(refPrice.start, 8.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = "${price?.name}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )

            Text(
                text = "${price?.measure}",
                fontSize = 14.sp
            )
        }
        Divider(
            modifier = Modifier.constrainAs(refDivider) {
                start.linkTo(refIcon.end, 8.dp)
                top.linkTo(refInfo.bottom, 8.dp)
            }
        )
        Row(modifier = Modifier.constrainAs(refPrice) {
            end.linkTo(parent.end, 8.dp)
            top.linkTo(parent.top, 8.dp)
            bottom.linkTo(parent.bottom, 8.dp)
        }) {
            if (price?.price_secondary.isNullOrEmpty()){
                Price(
                    text = "${price?.price_primary}",
                    isHigh = null
                )
            } else {
                Price(
                    text = "${price?.price_primary}",
                    isHigh = false
                )
                Price(
                    text = "${price?.price_secondary}",
                    isHigh = true
                )
            }
        }
    }
}

@Composable
@Preview
fun Price(
    modifier: Modifier = Modifier,
    text: String = "",
    isHigh: Boolean? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        when(isHigh){
            true -> {
                Icon(
                    Icons.Default.ArrowDropUp,
                    contentDescription = "high_price",
                    tint = Color.Green
                )
            }
            false -> {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "low_price",
                    tint = Color.Red
                )
            }
            else -> {}
        }
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}













