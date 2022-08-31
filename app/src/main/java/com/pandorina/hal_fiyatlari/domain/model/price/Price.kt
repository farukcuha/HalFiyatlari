package com.pandorina.hal_fiyatlari.domain.model.price



data class Price(
    val name: String? = null,
    val icon: String? = null,
    val measure: String? = null,
    val price_primary: String? = null,
    val price_secondary: String? = null
)