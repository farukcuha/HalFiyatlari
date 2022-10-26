package com.pandorina.hal_fiyatlar.presentation.screens.earnings

import com.pandorina.hal_fiyatlar.core.BaseUiState

data class AddEarningsUiState(
    val nameInputPasts: List<String> = listOf(),
    val unitPriceInputPasts: List<String> = listOf(),
    val totalCaseCountInputPasts: List<String> = listOf(),
    val caseWeightInputPasts: List<String> = listOf(),
    val commissionPercentageInputPasts: List<String> = listOf(),
    override var isLoading: Boolean = false,
    override var error: String? = ""
): BaseUiState()
