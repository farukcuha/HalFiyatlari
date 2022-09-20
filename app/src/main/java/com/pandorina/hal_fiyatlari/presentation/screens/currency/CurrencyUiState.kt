package com.pandorina.hal_fiyatlari.presentation.screens.currency

import com.pandorina.hal_fiyatlari.core.BaseUiState
import com.pandorina.hal_fiyatlari.domain.model.currency.Currency

data class CurrencyUiState(
    val currencies: List<Currency>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""
): BaseUiState()
