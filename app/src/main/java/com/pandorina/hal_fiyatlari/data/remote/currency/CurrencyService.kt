package com.pandorina.hal_fiyatlari.data.remote.currency

import io.ktor.client.statement.*

interface CurrencyService {
    companion object {
        const val ROUTE_CURRENCY = "/currency"
    }

    suspend fun getCurrencies(): HttpResponse
}