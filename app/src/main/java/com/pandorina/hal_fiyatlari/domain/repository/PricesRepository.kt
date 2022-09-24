package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.domain.model.price.PriceResponse
import kotlinx.coroutines.flow.Flow

interface PricesRepository {

    suspend fun getPriceDates(cityId: String?): Flow<Result<List<String>>>

    suspend fun getPricesByDate(cityId: String?, date: String?): Flow<Result<PriceResponse>>
}