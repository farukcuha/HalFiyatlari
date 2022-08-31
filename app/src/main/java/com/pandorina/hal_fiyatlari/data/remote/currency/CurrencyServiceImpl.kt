package com.pandorina.hal_fiyatlari.data.remote.currency

import com.pandorina.hal_fiyatlari.data.remote.news.NewsService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class CurrencyServiceImpl(
    val client: HttpClient
): CurrencyService {

    override suspend fun getCurrencies(): HttpResponse {
        return client.get(NewsService.ROUTE_NEWS)
    }
}