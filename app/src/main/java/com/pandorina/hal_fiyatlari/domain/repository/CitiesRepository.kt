package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.data.remote.city.dto.CityDto
import com.pandorina.hal_fiyatlari.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    fun getCities(): Flow<NetworkResult<List<CityDto>>>
}