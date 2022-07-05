package com.pandorina.hal_fiyatlari.data.remote.price.dto

import com.pandorina.hal_fiyatlari.domain.model.price.Price
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("name")
    val name: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("measure")
    val measure: String?,
    @SerialName("price_primary")
    val pricePrimary: String?,
    @SerialName("price_secondary")
    val priceSecondary: String?
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
