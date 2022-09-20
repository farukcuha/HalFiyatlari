package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.remote.city.CitiesService
import com.pandorina.hal_fiyatlari.data.remote.city.dto.CityDto
import com.pandorina.hal_fiyatlari.domain.model.city.City
import com.pandorina.hal_fiyatlari.domain.repository.CitiesRepository
import com.pandorina.hal_fiyatlari.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class CitiesRepositoryImpl(
    private val citiesService: CitiesService
): CitiesRepository {

    override fun getCities(): Flow<Result<List<City>>> = channelFlow {
        safeApiCall<List<CityDto>> {
            citiesService.getCities().call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success<List<City>>(
                    it.map { cityDto -> cityDto.toDomain() }
                ))
            }.onFailure {
                trySend(Result.failure<List<City>>(it))
            }
        }
    }
}