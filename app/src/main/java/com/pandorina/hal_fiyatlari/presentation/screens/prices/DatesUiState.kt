package com.pandorina.hal_fiyatlari.presentation.screens.prices

import com.pandorina.hal_fiyatlari.core.BaseUiState

data class DatesUiState(
    override var isLoading: Boolean = false,
    override var error: String? = ""): BaseUiState()