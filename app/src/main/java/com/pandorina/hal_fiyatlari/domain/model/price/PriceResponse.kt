package com.pandorina.hal_fiyatlari.domain.model.price

data class PriceResponse(
    val cityId: Int?,
    val title: String?,
    val date: String?,
    val size: Int?,
    val prices: List<Price?>
)