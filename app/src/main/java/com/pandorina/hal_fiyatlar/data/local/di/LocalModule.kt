package com.pandorina.hal_fiyatlar.data.local.di

import android.content.Context
import androidx.room.Room
import com.pandorina.hal_fiyatlar.data.local.dao.EarningsDao
import com.pandorina.hal_fiyatlar.data.local.AppDatabase
import com.pandorina.hal_fiyatlar.data.local.dao.EarningInputPastsDao
import com.pandorina.hal_fiyatlar.data.local.dao.FavoriteCitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideEarningsDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "earnings_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEarningsDao(
        appDatabase: AppDatabase
    ): EarningsDao {
        return appDatabase.getEarningsDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteCitiesDao(
        appDatabase: AppDatabase
    ): FavoriteCitiesDao {
        return appDatabase.getFavoriteCitiesDao()
    }

    @Provides
    @Singleton
    fun provideEarningInputPastsDao(
        appDatabase: AppDatabase
    ): EarningInputPastsDao {
        return  appDatabase.getEarningInputPastsDao()
    }
}