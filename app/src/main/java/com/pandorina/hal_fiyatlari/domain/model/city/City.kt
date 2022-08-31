package com.pandorina.hal_fiyatlari.domain.model.city


data class City(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val isFavorite: Boolean = false,
    val isActive: Boolean = true,
    val priority: Int? = 0
)
