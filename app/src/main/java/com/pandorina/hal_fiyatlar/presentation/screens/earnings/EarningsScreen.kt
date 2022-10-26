package com.pandorina.hal_fiyatlar.presentation.screens.earnings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandorina.hal_fiyatlar.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlar.presentation.component.MenuAction
import com.pandorina.hal_fiyatlar.presentation.component.MenuIcon
import com.pandorina.hal_fiyatlar.presentation.navigation.NavigationRoutes
import com.pandorina.hal_fiyatlar.presentation.component.CustomDialog
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.EarningButton
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.EarningItem
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.components.StatisticalEarningView
import com.pandorina.hal_fiyatlar.presentation.theme.black

data class Statistic(val title: String, val income: Float)

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun EarningsScreen(
    navController: NavController? = rememberNavController()
) {
    val viewModel: EarningsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.value
    val earningIdToBeDeleted = remember { mutableStateOf<Int?>(null)  }
    var showClearDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Kazanç Arşivim",
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController?.popBackStack()
                    }
                },
                actions = {
                    MenuIcon(action = MenuAction.Clear) {
                        showClearDialog = true
                    }
                }
            )
        },
        content = {
            Column {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    val list = listOf(
                        Statistic(
                            "Bugün",
                            uiState.dailySum ?: 0f
                        ),
                        Statistic(
                            "Bu Hafta",
                            uiState.weeklySum ?: 0f
                        ),
                        Statistic(
                            "Bu Ay",
                            uiState.monthlySum ?: 0f
                        ),
                        Statistic(
                            "Toplam",
                            uiState.totalSum ?: 0f
                        ),
                    )
                    items(list.size) {
                        StatisticalEarningView(
                            list[it]
                        )
                    }
                }
                EarningButton(
                    color = black,
                    text = "Ekle",
                    modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp))
                {
                    mEarning = null
                    navController?.navigate(NavigationRoutes.AddEarning.route)
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    val list = uiState.earnings
                    items(list.size, key = {list[it].id ?: 0}) {
                        EarningItem(
                            modifier = Modifier
                                .animateItemPlacement(
                                animationSpec = tween(500)
                            ),
                            earning = list[it],
                            onClickEdit = { earning ->
                                mEarning = earning
                                navController?.navigate(NavigationRoutes.AddEarning.route)
                            },
                            onClickDelete = { earningId ->
                                earningIdToBeDeleted.value = earningId
                            }
                        )
                        if (earningIdToBeDeleted.value != null) CustomDialog(
                            title = "Sil",
                            text = "Silmek istediğinize emin misiniz?",
                            confirmButtonText = "Sil",
                            onConfirm = {
                                viewModel.deleteEarning(earningIdToBeDeleted.value ?: return@CustomDialog)
                                earningIdToBeDeleted.value = null
                            },
                            confirmButtonColor = Color.Red,
                            dismissButtonText = "İptal",
                            dismissButtonColor = black,
                            onDismiss = {
                                earningIdToBeDeleted.value = null
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }

            if (showClearDialog) CustomDialog(
                title = "Sil",
                text = "Tüm kayıtlarınızı silmek istediğinize emin misiniz? Bu işlem geri alınamaz!",
                confirmButtonText = "Sil",
                onConfirm = {
                    viewModel.clear()
                    showClearDialog = false
                },
                confirmButtonColor = Color.Red,
                dismissButtonText = "İptal",
                dismissButtonColor = black,
                onDismiss = {
                    showClearDialog = false
                }
            )
        }
    )
}

