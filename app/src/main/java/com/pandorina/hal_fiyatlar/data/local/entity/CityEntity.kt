package com.pandorina.hal_fiyatlar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandorina.hal_fiyatlar.domain.model.city.City

@Entity(tableName = "favorite_cities_table")
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = null,
    @ColumnInfo(name = "srcUrl")
    val srcUrl: String? = null,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
){
    fun toCity(): City {
        return City(
            id = id,
            title = title,
            imageUrl = imageUrl,
            srcUrl = srcUrl,
            isFavorite = isFavorite
        )
    }
}
