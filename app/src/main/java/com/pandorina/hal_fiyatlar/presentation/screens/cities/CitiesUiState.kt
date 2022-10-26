package com.pandorina.hal_fiyatlar.presentation.screens.cities

import com.pandorina.hal_fiyatlar.domain.model.city.City
import com.pandorina.hal_fiyatlar.core.BaseUiState

data class CitiesUiState(
    val cities: List<City?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""
) : BaseUiState()
