package com.pandorina.hal_fiyatlari.data.remote.photo.dto

import androidx.annotation.Keep
import com.pandorina.hal_fiyatlari.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlari.domain.model.photo.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
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