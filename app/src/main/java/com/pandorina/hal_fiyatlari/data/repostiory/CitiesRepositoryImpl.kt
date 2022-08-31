package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.remote.city.CitiesService
import com.pandorina.hal_fiyatlari.data.remote.city.dto.CityDto
import com.pandorina.hal_fiyatlari.domain.repository.CitiesRepository
import com.pandorina.hal_fiyatlari.util.NetworkResult
import com.pandorina.hal_fiyatlari.util.safeApiCall
import io.ktor.client.call.*
import kotlinx.coroutines.flow.Flow

class CitiesRepositoryImpl(
    private val citiesService: CitiesService
): CitiesRepository {

    override fun getCities(): Flow<NetworkResult<List<CityDto>>> {
        return safeApiCall { citiesService.getCities().body() }
    }
}