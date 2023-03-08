package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.local.dao.FavoriteCitiesDao
import com.pandorina.hal_fiyatlar.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlar.domain.repository.FavoriteCitiesRepository
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