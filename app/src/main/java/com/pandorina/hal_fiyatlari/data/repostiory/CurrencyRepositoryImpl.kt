package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.remote.currency.CurrencyService
import com.pandorina.hal_fiyatlari.domain.repository.CurrencyRepository
import io.ktor.client.statement.*

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService
): CurrencyRepository {
    override suspend fun getCurrencies(): HttpResponse {
        return currencyService.getCurrencies()
    }
}