package com.pandorina.hal_fiyatlari.data.remote.news.dto

import com.pandorina.hal_fiyatlari.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlari.domain.model.news.Category
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("srcUrl")
    val srcUrl: String,
) : BaseDto<Category>() {

    override fun toDomain(): Category {
        return Category(
            id = id,
            title = title,
            srcUrl = srcUrl
        )
    }
}
