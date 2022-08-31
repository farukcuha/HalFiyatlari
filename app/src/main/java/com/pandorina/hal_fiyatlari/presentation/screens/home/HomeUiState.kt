package com.pandorina.hal_fiyatlari.presentation.screens.home

import com.pandorina.hal_fiyatlari.domain.model.photo.Photo
import com.pandorina.hal_fiyatlari.util.BaseUiState

data class HomeUiState(
    var photo: Photo? = null,
    override var isLoading: Boolean = false,
    override var error: String? = null
) : BaseUiState()