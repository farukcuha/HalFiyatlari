package com.pandorina.hal_fiyatlar.data.remote.photo.dto

import com.pandorina.hal_fiyatlar.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlar.domain.model.photo.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("title")
    val title: String? = null
): BaseDto<Photo>(){

    override fun toDomain(): Photo {
        return Photo(
            imageUrl = imageUrl,
            title = title
        )
    }
}