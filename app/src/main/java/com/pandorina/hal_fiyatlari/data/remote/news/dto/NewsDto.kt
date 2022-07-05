package com.pandorina.hal_fiyatlari.data.remote.news.dto

import com.pandorina.hal_fiyatlari.data.remote.price.dto.BaseDto
import com.pandorina.hal_fiyatlari.domain.model.news.News
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    @SerialName("time")
    val time: String?,
    @SerialName("path")
    val path: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("content")
    val content: String?
): BaseDto<News>() {

    override fun toDomain(): News {
        return News(
            time = time,
            path = path,
            image = image,
            title = title,
            content = content
        )
    }
}