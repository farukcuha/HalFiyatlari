package com.pandorina.hal_fiyatlar.data.remote.city.dto

import com.pandorina.hal_fiyatlar.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlar.domain.model.city.City
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CityDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("isActive")
    val isActive: Boolean? = null,
    @SerialName("priority")
    val priority: Int? = null
): BaseDto<City>() {

    override fun toDomain(): City {
        return City(
            id = id,
            title = title,
            imageUrl = imageUrl,
            isActive = true,
            priority = priority
        )
    }
}
