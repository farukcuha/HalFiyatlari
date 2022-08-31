package com.pandorina.hal_fiyatlari.presentation.screens.prices

import com.pandorina.hal_fiyatlari.domain.model.price.Price
import com.pandorina.hal_fiyatlari.util.BaseUiState

data class PricesUiState(
    var prices: List<Price?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""): BaseUiState()
