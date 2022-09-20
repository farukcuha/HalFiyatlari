package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.domain.model.currency.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getCurrencies(): Flow<Result<List<Currency>>>
}