package com.pandorina.hal_fiyatlari.util

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

inline fun <reified T> safeApiCall(
    crossinline process: suspend () -> HttpClientCall
): Flow<Result<T>> = flow {
    try {
        val result = process.invoke()
        emit(Result.success<T>(result.body()))
    } catch (e: ClientRequestException) {
        emit(Result.failure<T>(e))
    } catch (e: ServerResponseException) {
        emit(Result.failure<T>(e))
    } catch (e: IOException) {
        emit(Result.failure<T>(e))
    } catch (e: SerializationException) {
        emit(Result.failure<T>(e))
    } catch (e: Exception) {
        emit(Result.failure<T>(e))
    }
}