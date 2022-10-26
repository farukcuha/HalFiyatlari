package com.pandorina.hal_fiyatlar.presentation.screens.earnings

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandorina.hal_fiyatlar.R
import com.pandorina.hal_fiyatlar.data.local.entity.EarningEntity
import com.pandorina.hal_fiyatlar.data.local.entity.EarningInputPastEntity
import com.pandorina.hal_fiyatlar.domain.model.earning.Earning
import com.pandorina.hal_fiyatlar.presentation.component.CustomDialog
import com.pandorina.hal_fiyatlar.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlar.presentation.component.MenuAction
import com.pandorina.hal_fiyatlar.presentation.component.MenuIcon
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.ChipGroup
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.EarningButton
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.EarningTextField
import com.pandorina.hal_fiyatlar.presentation.theme.black
import com.pandorina.hal_fiyatlar.util.toDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

var mEarning: Earning? = null

@Composable
@Preview
fun AddEarningScreen(
    navController: NavController? = rememberNavController(),
    earning: Earning? = mEarning
) {
    val context = LocalContext.current
    val viewModel: AddEarningViewModel = hiltViewModel()
    val uiState = viewModel.uiState.value
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Ekle - Güncelle",
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController?.popBackStack()
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                var name by remember { mutableStateOf(earning?.name ?: "") }
                var isErrorName by remember { mutableStateOf(false) }

                var unitPrice by remember { mutableStateOf(earning?.unitPrice?.toString() ?: "") }
                var isErrorUnitPrice by remember { mutableStateOf(false) }

                var totalCaseCount by remember {
                    mutableStateOf(
                        earning?.totalCaseCount?.toString() ?: ""
                    )
                }
                var isErrorTotalCaseCount by remember { mutableStateOf(false) }

                var caseWeight by remember { mutableStateOf(earning?.caseWeight?.toString() ?: "") }
                var isErrorCaseWeight by remember { mutableStateOf(false) }

                var commissionPercentage by remember {
                    mutableStateOf(
                        earning?.commissionPercentage?.toString() ?: ""
                    )
                }
                var isErrorCommissionPercentage by remember { mutableStateOf(false) }

                var time by remember {
                    mutableStateOf(
                        earning?.timeStamp ?: System.currentTimeMillis()
                    )
                }
                var totalIncome by remember {
                    mutableStateOf(
                        earning?.totalIncome?.toString() ?: ""
                    )
                }

                try {
                    val income =
                        (unitPrice.toPriceValue() * totalCaseCount.toPriceValue() * caseWeight.toPriceValue())
                    totalIncome =
                        (income - income * (commissionPercentage.toPriceValue() / 100)).toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                AnimatedVisibility(
                    visible = totalIncome.isNotEmpty(),
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(
                        text = "Net : ${totalIncome.toPriceValue()} ₺",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }
                EarningTextField(
                    label = "Ürün Adı",
                    onValueChange = { name = it },
                    text = name,
                    keyboardType = KeyboardType.Text,
                    isError = isErrorName,
                    onClickClear = { name = "" }
                )
                AnimatedVisibility(visible = uiState.nameInputPasts.isNotEmpty()) {
                    ChipGroup(
                        modifier = Modifier.padding(top = 8.dp),
                        labels = uiState.nameInputPasts,
                        onClickClear = { viewModel.deleteInputPasts(EarningInputPastEntity.Field.NAME) }
                    ){
                        name = it
                    }
                }
                EarningTextField(
                    label = "Birim Fiyat",
                    onValueChange = { unitPrice = it },
                    text = unitPrice,
                    keyboardType = KeyboardType.Number,
                    isError = isErrorUnitPrice,
                    onClickClear = { unitPrice = "" }
                )
                AnimatedVisibility(visible = uiState.unitPriceInputPasts.isNotEmpty()) {
                    ChipGroup(
                        modifier = Modifier.padding(top = 8.dp),
                        labels = uiState.unitPriceInputPasts,
                        onClickClear = { viewModel.deleteInputPasts(EarningInputPastEntity.Field.UNIT_PRICE) }
                    ){
                        unitPrice = it
                    }
                }
                EarningTextField(
                    label = "Toplam Kasa Sayısı",
                    onValueChange = { totalCaseCount = it },
                    text = totalCaseCount,
                    keyboardType = KeyboardType.Number,
                    isError = isErrorTotalCaseCount,
                    onClickClear = { totalCaseCount = "" }
                )
                AnimatedVisibility(visible = uiState.totalCaseCountInputPasts.isNotEmpty()) {
                    ChipGroup(
                        modifier = Modifier.padding(top = 8.dp),
                        labels = uiState.totalCaseCountInputPasts,
                        onClickClear = { viewModel.deleteInputPasts(EarningInputPastEntity.Field.TOTAL_CASE_COUNT) }
                    ){
                        totalCaseCount = it
                    }
                }
                EarningTextField(
                    label = "Kasa Kaç Kilogram?",
                    onValueChange = { caseWeight = it },
                    text = caseWeight,
                    keyboardType = KeyboardType.Number,
                    isError = isErrorCaseWeight,
                    onClickClear = { caseWeight = "" }
                )
                AnimatedVisibility(visible = uiState.caseWeightInputPasts.isNotEmpty()) {
                    ChipGroup(
                        modifier = Modifier.padding(top = 8.dp),
                        labels = uiState.caseWeightInputPasts,
                        onClickClear = { viewModel.deleteInputPasts(EarningInputPastEntity.Field.CASE_WEIGHT) }
                    ){
                        caseWeight = it
                    }
                }
                EarningTextField(
                    label = "Komisyon Oranı (Yüzde)",
                    onValueChange = { commissionPercentage = it },
                    text = commissionPercentage,
                    keyboardType = KeyboardType.Number,
                    isError = isErrorCommissionPercentage,
                    imeAction = ImeAction.Done,
                    onClickClear = { commissionPercentage = "" }
                )
                AnimatedVisibility(visible = uiState.commissionPercentageInputPasts.isNotEmpty()) {
                    ChipGroup(
                        modifier = Modifier.padding(top = 8.dp),
                        labels = uiState.commissionPercentageInputPasts,
                        onClickClear = { viewModel.deleteInputPasts(EarningInputPastEntity.Field.COMMISSION_PERCENTAGE) }
                    ){
                        commissionPercentage = it
                    }
                }
                OutlinedButton(
                    onClick = {
                        context.datePickerDialog(Calendar.getInstance().apply {
                            timeInMillis = time
                        }) { millis ->
                            time = millis
                        }.show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    Text(text = time.toDate(), color = Color.Black)
                }

                EarningButton(
                    color = black,
                    text = "Kaydet",
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    if (name.isEmpty()) {
                        isErrorName = true
                        return@EarningButton
                    } else isErrorName = false

                    if (unitPrice.isEmpty()) {
                        isErrorUnitPrice = true
                        return@EarningButton
                    } else isErrorUnitPrice = false

                    if (totalCaseCount.isEmpty()) {
                        isErrorTotalCaseCount = true
                        return@EarningButton
                    } else isErrorTotalCaseCount = false

                    if (caseWeight.isEmpty()) {
                        isErrorCaseWeight = true
                        return@EarningButton
                    } else isErrorCaseWeight = false

                    if (commissionPercentage.isEmpty()) {
                        isErrorCommissionPercentage = true
                        return@EarningButton
                    } else isErrorCommissionPercentage = false

                    viewModel.insertEarning(
                        EarningEntity(
                            id = earning?.id,
                            name = name,
                            unitPrice = unitPrice.toPriceValue(),
                            totalCaseCount = totalCaseCount.toPriceValue(),
                            caseWeight = caseWeight.toPriceValue(),
                            commissionPercentage = commissionPercentage.toPriceValue(),
                            totalIncome = totalIncome.toPriceValue(),
                            timeStamp = time
                        )
                    )
                    navController?.popBackStack()
                }

                if (earning != null) {
                    EarningButton(
                        color = Color.Red,
                        text = "Sil",
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        showDeleteDialog = true
                    }
                }

                if (showDeleteDialog) CustomDialog(
                    title = "Sil",
                    text = "Silmek istediğinize emin misiniz?",
                    confirmButtonText = "Sil",
                    onConfirm = {
                        viewModel.deleteEarning(earning?.id!!)
                        navController?.popBackStack()
                        showDeleteDialog = false
                    },
                    confirmButtonColor = Color.Red,
                    dismissButtonText = "İptal",
                    dismissButtonColor = black,
                    onDismiss = {
                        showDeleteDialog = false
                    }
                )
            }
        }
    )
}

private fun String.toPriceValue(): Float {
    val actualValue = replace(",", ".")
        .replace(" ", "")
        .replace("-", "")
    return BigDecimal(actualValue).setScale(2, RoundingMode.HALF_EVEN).toFloat()
}

private fun Context.datePickerDialog(date: Calendar?, onSelectDate: (Long) -> Unit): DatePickerDialog {
    val currentDate = date ?: Calendar.getInstance()
    return DatePickerDialog(
        this,
        R.style.DialogTheme,
        { d, year, montOfYear, dayOfMonth ->
            onSelectDate(
                currentDate.apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, montOfYear)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }.timeInMillis
            )
        },
        currentDate.get(Calendar.YEAR),
        currentDate.get(Calendar.MONTH),
        currentDate.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.maxDate = Date().time
    }
}
