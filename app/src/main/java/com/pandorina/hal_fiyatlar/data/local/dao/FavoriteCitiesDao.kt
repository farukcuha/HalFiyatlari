package com.pandorina.hal_fiyatlar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pandorina.hal_fiyatlar.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCity(cityEntity: CityEntity)

    @Query("DELETE FROM favorite_cities_table WHERE :cityId = id")
    suspend fun deleteFavoriteCity(cityId: String)

    @Query("SELECT * FROM favorite_cities_table")
    fun getFavoriteCities(): Flow<List<CityEntity>>
}