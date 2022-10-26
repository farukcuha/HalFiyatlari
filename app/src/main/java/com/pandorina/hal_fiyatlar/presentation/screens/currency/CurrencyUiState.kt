package com.pandorina.hal_fiyatlar.presentation.screens.currency

import com.pandorina.hal_fiyatlar.core.BaseUiState
import com.pandorina.hal_fiyatlar.domain.model.currency.Currency

data class CurrencyUiState(
    val currencies: List<Currency>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""
): BaseUiState()
