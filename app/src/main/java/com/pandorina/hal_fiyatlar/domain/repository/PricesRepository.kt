package com.pandorina.hal_fiyatlar.domain.repository

import com.pandorina.hal_fiyatlar.domain.model.price.PriceResponse
import kotlinx.coroutines.flow.Flow

interface PricesRepository {

    suspend fun getPriceDates(cityId: String?): Flow<Result<List<String>>>

    suspend fun getPricesByDate(cityId: String?, date: String?): Flow<Result<PriceResponse>>
}