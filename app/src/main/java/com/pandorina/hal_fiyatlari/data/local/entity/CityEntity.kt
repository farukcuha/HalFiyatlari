package com.pandorina.hal_fiyatlari.data.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandorina.hal_fiyatlari.domain.model.city.City

@Keep
@Entity(tableName = "favorite_cities_table")
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
){
    fun toCity(): City {
        return City(
            id = id,
            title = title,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
    }
}
