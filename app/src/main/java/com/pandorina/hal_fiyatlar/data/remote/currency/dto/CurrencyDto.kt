package com.pandorina.hal_fiyatlar.data.remote.currency.dto

import com.pandorina.hal_fiyatlar.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlar.domain.model.currency.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDto(
    @SerialName("name")
    val name: String?,
    @SerialName("price")
    val price: String?,
    @SerialName("changeRate")
    val changeRate: String?,
    @SerialName("changeAmount")
    val changeAmount: String?,
    @SerialName("trendUp")
    val trendUp: Boolean?
): BaseDto<Currency>() {

    override fun toDomain(): Currency {
        return Currency(
            name = name,
            price = price,
            changeRate = changeRate,
            changeAmount = changeAmount,
            trendUp = trendUp
        )
    }
}
