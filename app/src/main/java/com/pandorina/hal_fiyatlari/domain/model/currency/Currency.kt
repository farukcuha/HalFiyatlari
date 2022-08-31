package com.pandorina.hal_fiyatlari.domain.model.currency

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val name: String?,
    val price: String?,
    val changeRate: String?,
    val changeAmount: String?,
    val trend: Int?
)
