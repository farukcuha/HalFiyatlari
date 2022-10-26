package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.remote.currency.CurrencyService
import com.pandorina.hal_fiyatlar.data.remote.currency.dto.CurrencyDto
import com.pandorina.hal_fiyatlar.domain.model.currency.Currency
import com.pandorina.hal_fiyatlar.domain.repository.CurrencyRepository
import com.pandorina.hal_fiyatlar.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService
): CurrencyRepository {
    override suspend fun getCurrencies(): Flow<Result<List<Currency>>> = channelFlow {
        safeApiCall<List<CurrencyDto>> {
            currencyService.getCurrencies().call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success<List<Currency>>(it.map { currencyDto -> currencyDto.toDomain() }))
            }.onFailure {
                trySend(Result.failure<List<Currency>>(it))
            }
        }
    }
}