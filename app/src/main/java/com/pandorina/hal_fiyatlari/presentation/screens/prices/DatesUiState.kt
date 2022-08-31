package com.pandorina.hal_fiyatlari.presentation.screens.prices

import com.pandorina.hal_fiyatlari.util.BaseUiState

data class DatesUiState(
    var dates: List<String?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""): BaseUiState()