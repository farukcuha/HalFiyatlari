package com.pandorina.hal_fiyatlar.domain.model.price


data class Price(
    val name: String?,
    val icon: String?,
    val measure: String?,
    val price_primary: String?,
    val price_secondary: String?
)