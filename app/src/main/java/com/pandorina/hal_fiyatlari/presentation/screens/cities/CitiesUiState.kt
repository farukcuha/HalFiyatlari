package com.pandorina.hal_fiyatlari.presentation.screens.cities

import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.core.BaseUiState

data class CitiesUiState(
    val cities: List<City?>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = ""
) : BaseUiState()
