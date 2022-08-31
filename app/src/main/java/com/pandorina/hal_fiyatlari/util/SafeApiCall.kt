package com.pandorina.hal_fiyatlari.util

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

inline fun <reified T> safeApiCall(
    crossinline process: suspend () -> HttpClientCall
): Flow<NetworkResult<T>> = flow {
    emit(NetworkResult.Loading())
    try {
        val result = process.invoke()
        emit(NetworkResult.Success<T>(result.body()))
    } catch (e: ClientRequestException) {
        emit(NetworkResult.Error<T>(e.localizedMessage))
    } catch (e: ServerResponseException) {
        emit(NetworkResult.Error<T>(e.localizedMessage))
    } catch (e: IOException) {
        emit(NetworkResult.Error<T>(e.localizedMessage))
    } catch (e: SerializationException) {
        emit(NetworkResult.Error<T>(e.localizedMessage))
    } catch (e: Exception) {
        emit(NetworkResult.Error<T>(e.localizedMessage))
    }
}