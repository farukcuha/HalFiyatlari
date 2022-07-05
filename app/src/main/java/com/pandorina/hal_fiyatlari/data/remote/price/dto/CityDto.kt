package com.pandorina.hal_fiyatlari.data.remote.price.dto

import com.pandorina.hal_fiyatlari.domain.model.price.City
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("srcUrl")
    val srcUrl: String
): BaseDto<City>(){

    override fun toDomain(): City {
        return City(
            id = id,
            title = title,
            imageUrl = imageUrl,
            srcUrl = srcUrl
        )
    }
}