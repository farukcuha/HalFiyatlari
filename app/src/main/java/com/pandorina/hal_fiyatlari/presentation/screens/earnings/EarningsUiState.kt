package com.pandorina.hal_fiyatlari.presentation.screens.earnings

import com.pandorina.hal_fiyatlari.core.BaseUiState
import com.pandorina.hal_fiyatlari.domain.model.earning.Earning

data class EarningsUiState(
    val earnings: List<Earning> = emptyList(),
    val dailySum: Float? = null,
    val weeklySum: Float? = null,
    val monthlySum: Float? = null,
    val totalSum: Float? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""
): BaseUiState()
