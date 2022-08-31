package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.local.FavoriteCitiesDao
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.domain.repository.FavoriteCitiesRepository
import kotlinx.coroutines.flow.Flow

class FavoriteCitiesRepositoryImpl(
    private val favoriteCitiesDao: FavoriteCitiesDao
): FavoriteCitiesRepository {

    override suspend fun insertFavoriteCity(cityEntity: CityEntity) {
        return favoriteCitiesDao.insertFavoriteCity(cityEntity)
    }

    override suspend fun deleteFavoriteCity(cityId: String) {
        return favoriteCitiesDao.deleteFavoriteCity(cityId)
    }

    override fun getFavoriteCities(): Flow<List<CityEntity>> {
        return favoriteCitiesDao.getFavoriteCities()
    }
}