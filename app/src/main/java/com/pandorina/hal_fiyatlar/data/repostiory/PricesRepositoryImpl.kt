package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.remote.price.PricesService
import com.pandorina.hal_fiyatlar.data.remote.price.dto.PriceResponseDto
import com.pandorina.hal_fiyatlar.domain.model.price.PriceResponse
import com.pandorina.hal_fiyatlar.domain.repository.PricesRepository
import com.pandorina.hal_fiyatlar.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class PricesRepositoryImpl(
    private val pricesService: PricesService
): PricesRepository {

    override suspend fun getPriceDates(cityId: String?): Flow<Result<List<String>>> = channelFlow {
        safeApiCall<List<String>> {
            pricesService.getPriceDates(cityId).call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success(
                    it
                ))
            }.onFailure {
                trySend(Result.failure(it))
            }
        }
    }

    override suspend fun getPricesByDate(cityId: String?, date: String?): Flow<Result<PriceResponse>> = channelFlow {
        safeApiCall<PriceResponseDto> {
            pricesService.getPrices(cityId, date).call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success(it.toDomain()))
            }.onFailure {
                trySend(Result.failure(it))
            }
        }
    }
}