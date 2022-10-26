package com.pandorina.hal_fiyatlar.presentation.screens.prices

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.pandorina.hal_fiyatlar.R
import com.pandorina.hal_fiyatlar.domain.model.price.Price
import com.pandorina.hal_fiyatlar.presentation.component.*
import com.pandorina.hal_fiyatlar.presentation.theme.white
import com.pandorina.hal_fiyatlar.util.InterstitialAdManager

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PricesScreen(
    navController: NavController? = null,
) {
    val viewModel: PricesViewModel = hiltViewModel()
    val uiState = viewModel.uiState.value
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState().value

    var showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var visibleSearch by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "${viewModel.title.value} Hal Fiyatları",
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        if (visibleSearch) {
                            visibleSearch = false
                            viewModel.searchQuery.value = ""
                            viewModel.filterPrices()
                        }
                        else navController?.popBackStack()
                    }
                },
                actions = {
                    if (visibleSearch){
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .onFocusChanged { focusState ->
                                    showClearButton = (focusState.isFocused)
                                }
                                .focusRequester(focusRequester),
                            value = viewModel.searchQuery.value,
                            onValueChange = {
                                viewModel.searchQuery.value = it
                                viewModel.filterPrices()
                            },
                            placeholder = {
                                Text(text = "Ara")
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = Color.Transparent,
                                cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                            ),
                            trailingIcon = {
                                AnimatedVisibility(
                                    visible = showClearButton,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    IconButton(onClick = {
                                        if (viewModel.searchQuery.value.isEmpty()) visibleSearch = false
                                        else {
                                            viewModel.searchQuery.value = ""
                                            viewModel.filterPrices()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Close,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                keyboardController?.hide()
                            }),
                        )
                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }
                    } else MenuIcon(action = MenuAction.Search) {
                        visibleSearch = true
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column {
                    CurrentDate(dates = uiState.dates){
                        viewModel.getPrices(viewModel.cityId.value, it)
                    }
                    uiState.filteredPrices?.let { prices ->
                        LazyColumn {
                            if (prices.firstOrNull()?.price_secondary != null) item {
                                PriceInfoView()
                            }
                            items(prices.size, key = {
                                prices[it]?.name ?: ""
                            }) {
                                if (it % 10 == 0 && lifecycleState == Lifecycle.Event.ON_RESUME){
                                    AndroidView(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        factory = { context ->
                                            AdView(context).apply {
                                                setAdSize(AdSize.BANNER)
                                                adUnitId = context.getString(R.string.BANNER_AD_UNIT_ID)
                                                loadAd(InterstitialAdManager.adRequest)
                                            }
                                        }
                                    )
                                }
                                PriceItem(
                                    prices[it],
                                    modifier = Modifier.animateItemPlacement(TweenSpec(
                                        durationMillis = 500
                                    ))
                                )
                            }
                        }
                    }
                }
                if (uiState.isLoading) {
                    LoadingBar(modifier = Modifier.align(Center))
                }
                if (uiState.filteredPrices?.isEmpty() == true && viewModel.searchQuery.value.isNotEmpty()) {
                    Text(
                        fontSize = 16.sp,
                        text = "\"${viewModel.searchQuery.value}\" sorgusunu içeren ürün bulunamadı.",
                        modifier = Modifier.align(Center).padding(16.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun Lifecycle.observeAsState(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsState.addObserver(observer)
        onDispose {
            this@observeAsState.removeObserver(observer)
        }
    }
    return state
}

@OptIn(ExperimentalMaterialApi::class)
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
    Card(
        onClick = {
            expanded = true
        },
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = dates?.getOrNull(selectedIndex) ?: "Kayıtlı fiyat bulunmuyor.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Center)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 360.dp)
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
}

@Composable
fun PriceItem(
    price: Price?,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
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

            if (price?.measure?.isNotEmpty() == true){
                Text(
                    text = price.measure,
                    fontSize = 14.sp
                )
            }
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













