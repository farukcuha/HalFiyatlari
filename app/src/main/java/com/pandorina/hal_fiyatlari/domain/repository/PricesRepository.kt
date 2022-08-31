package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.data.remote.price.dto.PriceDto
import com.pandorina.hal_fiyatlari.domain.model.price.PriceDate

interface PricesRepository {

    suspend fun getPriceDates(cityId: String?): Result<List<PriceDate>>

    suspend fun getPricesByDate(cityId: String?, date: String?): Result<List<PriceDto>>
}