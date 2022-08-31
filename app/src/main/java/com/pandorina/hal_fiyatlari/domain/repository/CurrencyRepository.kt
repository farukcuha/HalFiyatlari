package com.pandorina.hal_fiyatlari.domain.repository

import io.ktor.client.statement.*

interface CurrencyRepository {

    suspend fun getCurrencies(): HttpResponse
}