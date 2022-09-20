package com.pandorina.hal_fiyatlari.presentation.screens.prices

import com.pandorina.hal_fiyatlari.domain.model.price.Price
import com.pandorina.hal_fiyatlari.core.BaseUiState

data class PricesUiState(
    var dates: List<String?>? = null,
    var prices: List<Price?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""): BaseUiState()
