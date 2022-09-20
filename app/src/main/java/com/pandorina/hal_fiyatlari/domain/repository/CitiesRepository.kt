package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.domain.model.city.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    fun getCities(): Flow<Result<List<City>>>
}