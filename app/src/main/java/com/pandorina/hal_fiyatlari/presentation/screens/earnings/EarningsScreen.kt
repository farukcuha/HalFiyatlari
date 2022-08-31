package com.pandorina.hal_fiyatlari.presentation.screens.earnings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandorina.hal_fiyatlari.domain.model.earning.Earning
import com.pandorina.hal_fiyatlari.presentation.component.CustomTopAppBar
import com.pandorina.hal_fiyatlari.presentation.component.MenuAction
import com.pandorina.hal_fiyatlari.presentation.component.MenuIcon
import com.pandorina.hal_fiyatlari.presentation.navigation.NavGraph
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.components.ConfirmationDialog
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.components.EarningButton
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.components.EarningItem
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.components.StatisticalEarningView
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.white


data class Statistic(val title: String, val income: Float)

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun EarningsScreen(
    navController: NavController? = rememberNavController()
) {
    val viewModel: EarningsViewModel = hiltViewModel()
    val earningIdToBeDeleted = remember { mutableStateOf<Int?>(null)  }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Kazanç Arşivim",
                navigationIcon = {
                    MenuIcon(action = MenuAction.Back) {
                        navController?.popBackStack()
                    }
                }
            )
        },
        content = {
            Column {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp)
                ) {
                    val list = listOf(
                        Statistic(
                            "Bugün",
                            viewModel.dailySumState.value ?: 0f
                        ),
                        Statistic(
                            "Bu Hafta",
                            viewModel.weeklySumState.value ?: 0f
                        ),
                        Statistic(
                            "Bu Ay",
                            viewModel.monthlySumState.value ?: 0f
                        ),
                        Statistic(
                            "Toplam",
                            viewModel.totalSumState.value ?: 0f
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
                    navController?.navigate(NavGraph.AddEarning.route)
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    val list = viewModel.earningsState.value
                    items(list.size, key = {list[it].id ?: 0}) {
                        EarningItem(
                            modifier = Modifier
                                .animateItemPlacement(
                                animationSpec = tween(500)
                            ),
                            earning = list[it],
                            onClickEdit = { earning ->
                                mEarning = earning
                                navController?.navigate(NavGraph.AddEarning.route)
                            },
                            onClickDelete = { earningId ->
                                earningIdToBeDeleted.value = earningId
                            }
                        )
                        if (earningIdToBeDeleted.value != null) ConfirmationDialog(
                            title = "Sil",
                            text = "Silmek istediğinize emin misiniz?",
                            confirmButtonText = "Sil",
                            onConfirm = {
                                viewModel.deleteEarning(earningIdToBeDeleted.value ?: return@ConfirmationDialog)
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
        }
    )
}

