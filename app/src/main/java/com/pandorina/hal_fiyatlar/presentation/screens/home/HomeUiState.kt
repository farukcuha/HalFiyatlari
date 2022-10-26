package com.pandorina.hal_fiyatlar.presentation.screens.home

import com.pandorina.hal_fiyatlar.domain.model.photo.Photo
import com.pandorina.hal_fiyatlar.core.BaseUiState

data class HomeUiState(
    var photo: Photo? = null,
    var isPricesActive: Boolean = true,
    var isEarningsActive: Boolean = true,
    var isNewsActive: Boolean = true,
    var isCurrencyActive: Boolean = true,
    var infoText: String = "",
    var showDialog: Boolean = false,
    override var isLoading: Boolean = false,
    override var error: String? = null
) : BaseUiState()