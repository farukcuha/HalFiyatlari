package com.pandorina.hal_fiyatlar.data.remote.currency

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class CurrencyServiceImpl(
    val client: HttpClient
): CurrencyService {

    override suspend fun getCurrencies(): HttpResponse {
        return client.get(CurrencyService.ROUTE_CURRENCY)
    }
}