package com.pandorina.hal_fiyatlar.domain.model.city


data class City(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val isFavorite: Boolean = false,
    val srcUrl: String?
)
