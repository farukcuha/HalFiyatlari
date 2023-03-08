package com.pandorina.hal_fiyatlar.data.remote.di

import android.util.Log
import com.pandorina.hal_fiyatlar.BuildConfig
import com.pandorina.hal_fiyatlar.data.remote.city.CitiesService
import com.pandorina.hal_fiyatlar.data.remote.city.CitiesServiceImpl
import com.pandorina.hal_fiyatlar.data.remote.currency.CurrencyService
import com.pandorina.hal_fiyatlar.data.remote.currency.CurrencyServiceImpl
import com.pandorina.hal_fiyatlar.data.remote.news.NewsService
import com.pandorina.hal_fiyatlar.data.remote.news.NewsServiceImpl
import com.pandorina.hal_fiyatlar.data.remote.photo.PhotoService
import com.pandorina.hal_fiyatlar.data.remote.photo.PhotoServiceImpl
import com.pandorina.hal_fiyatlar.data.remote.price.PricesService
import com.pandorina.hal_fiyatlar.data.remote.price.PricesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule{

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android){
            defaultRequest {
                host = BuildConfig.BASE_URL
                url { protocol = URLProtocol.HTTP }
            }

            install(ContentNegotiation){
                json()
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun providePricesService(
        httpClient: HttpClient
    ): PricesService{
        return PricesServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideNewsService(
        httpClient: HttpClient
    ): NewsService{
        return NewsServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideCurrencyService(
        httpClient: HttpClient
    ): CurrencyService{
        return CurrencyServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideCitiesService(
        httpClient: HttpClient
    ): CitiesService{
        return CitiesServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun providePhotoService(
        httpClient: HttpClient
    ): PhotoService{
        return PhotoServiceImpl(httpClient)
    }
}