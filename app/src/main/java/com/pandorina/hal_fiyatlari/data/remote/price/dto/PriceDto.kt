package com.pandorina.hal_fiyatlari.data.remote.price.dto

import com.pandorina.hal_fiyatlari.domain.model.price.Price
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("cityId")
    val cityId: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("lastUpdatedTime")
    val lastUpdatedTime: Long? = null,
    @SerialName("priceDate")
    val priceDate: String? = null,
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
