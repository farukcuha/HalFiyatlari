package com.pandorina.hal_fiyatlar.data.repostiory.di

import com.google.firebase.firestore.FirebaseFirestore
import com.pandorina.hal_fiyatlar.data.local.EarningInputPastsDao
import com.pandorina.hal_fiyatlar.data.local.EarningsDao
import com.pandorina.hal_fiyatlar.data.local.FavoriteCitiesDao
import com.pandorina.hal_fiyatlar.data.remote.city.CitiesService
import com.pandorina.hal_fiyatlar.data.remote.currency.CurrencyService
import com.pandorina.hal_fiyatlar.data.remote.news.NewsService
import com.pandorina.hal_fiyatlar.data.remote.photo.PhotoService
import com.pandorina.hal_fiyatlar.data.remote.price.PricesService
import com.pandorina.hal_fiyatlar.data.repostiory.*
import com.pandorina.hal_fiyatlar.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Provides
    @Singleton
    fun provideFirebaseFirestoreReference(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideCitiesRepository(
        citiesService: CitiesService
    ): CitiesRepository{
        return CitiesRepositoryImpl(citiesService)
    }

    @Provides
    @Singleton
    fun providePricesRepository(
        pricesService: PricesService
    ): PricesRepository{
        return PricesRepositoryImpl(pricesService)
    }

    @Provides
    @Singleton
    fun provideEarningsRepository(
        earningsDao: EarningsDao
    ): EarningRepository{
        return EarningRepositoryImpl(earningsDao)
    }

    @Provides
    @Singleton
    fun provideEarningsInputPastsRepository(
        earningInputPastsDao: EarningInputPastsDao
    ): EarningInputPastsRepository{
        return EarningInputPastsRepositoryImpl(earningInputPastsDao)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsService: NewsService
    ): NewsRepository{
        return NewsRepositoryImpl(newsService)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        currencyService: CurrencyService
    ): CurrencyRepository{
        return CurrencyRepositoryImpl(currencyService)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(
        photoService: PhotoService
    ): PhotoRepository{
        return PhotosRepositoryImpl(photoService)
    }

    @Provides
    @Singleton
    fun provideFavoriteCitiesRepository(
        favoriteCitiesDao: FavoriteCitiesDao
    ): FavoriteCitiesRepository{
        return FavoriteCitiesRepositoryImpl(favoriteCitiesDao)
    }
}