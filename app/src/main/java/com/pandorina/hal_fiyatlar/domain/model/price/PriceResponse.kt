package com.pandorina.hal_fiyatlar.domain.model.price


data class PriceResponse(
    val cityId: String?,
    val title: String?,
    val date: String?,
    val size: Int?,
    val prices: List<Price?>
)