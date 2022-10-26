package com.pandorina.hal_fiyatlar.presentation.screens.prices

import com.pandorina.hal_fiyatlar.domain.model.price.Price
import com.pandorina.hal_fiyatlar.core.BaseUiState

data class PricesUiState(
    var dates: List<String?>? = null,
    var prices: List<Price?>? = null,
    var filteredPrices: List<Price?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""): BaseUiState()
