package com.pandorina.hal_fiyatlari.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.data.local.entity.EarningEntity

@Database(entities = [EarningEntity::class, CityEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEarningsDao(): EarningsDao

    abstract fun getFavoriteCitiesDao(): FavoriteCitiesDao
}