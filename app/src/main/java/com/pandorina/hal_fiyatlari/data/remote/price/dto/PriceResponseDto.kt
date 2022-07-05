package com.pandorina.hal_fiyatlari.data.remote.price.dto

import com.pandorina.hal_fiyatlari.domain.model.price.PriceResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceResponseDto(
    @SerialName("city_id")
    val cityId: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("date")
    val date: String?,
    @SerialName("size")
    val size: Int?,
    @SerialName("prices")
    val prices: List<PriceDto?>
): BaseDto<PriceResponse>() {

    override fun toDomain(): PriceResponse {
        return PriceResponse(
            cityId = cityId,
            title = title,
            date = date,
            size = size,
            prices = prices.map { it?.toDomain() }
        )
    }
}