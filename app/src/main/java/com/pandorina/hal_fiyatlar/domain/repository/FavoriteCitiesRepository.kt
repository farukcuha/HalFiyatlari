package com.pandorina.hal_fiyatlar.domain.repository

import com.pandorina.hal_fiyatlar.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteCitiesRepository {

    suspend fun insertFavoriteCity(cityEntity: CityEntity)

    suspend fun deleteFavoriteCity(cityId: String)

    fun getFavoriteCities(): Flow<List<CityEntity>>
}