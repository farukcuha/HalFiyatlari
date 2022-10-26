package com.pandorina.hal_fiyatlar.data.remote.price.dto

import com.pandorina.hal_fiyatlar.domain.model.price.PriceResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceResponseDto(
    @SerialName("cityId")
    val cityId: String? = null,
    @SerialName("title")
    val title: String?? = null,
    @SerialName("date")
    val date: String?? = null,
    @SerialName("size")
    val size: Int?? = null,
    @SerialName("prices")
    val prices: List<PriceDto?> = emptyList()): BaseDto<PriceResponse>() {

    override fun toDomain(): PriceResponse {
        return PriceResponse(
            cityId = cityId,
            title = title,
            date = date,
            size = size,
            prices = prices.map {
                it?.toDomain()
            }
        )
    }
}
