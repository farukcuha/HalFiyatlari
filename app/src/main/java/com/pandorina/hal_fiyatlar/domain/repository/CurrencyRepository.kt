package com.pandorina.hal_fiyatlar.domain.repository

import com.pandorina.hal_fiyatlar.domain.model.currency.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getCurrencies(): Flow<Result<List<Currency>>>
}