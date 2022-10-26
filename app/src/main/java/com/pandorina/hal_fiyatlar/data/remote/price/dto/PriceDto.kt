package com.pandorina.hal_fiyatlar.data.remote.price.dto

import com.pandorina.hal_fiyatlar.domain.model.price.Price
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("icon")
    val icon: String? = null,
    @SerialName("measure")
    val measure: String? = null,
    @SerialName("pricePrimary")
    val pricePrimary: String? = null,
    @SerialName("priceSecondary")
    val priceSecondary: String? = null
): BaseDto<Price>(){
    override fun toDomain(): Price {
        return Price(
            name = name,
            icon = icon,
            measure = measure,
            price_primary = pricePrimary,
            price_secondary = priceSecondary
        )
    }
}