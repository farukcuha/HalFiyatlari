package com.pandorina.hal_fiyatlari.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pandorina.hal_fiyatlari.data.local.entity.CityEntity
import com.pandorina.hal_fiyatlari.data.local.entity.EarningEntity
import com.pandorina.hal_fiyatlari.data.local.entity.EarningInputPastEntity

@Database(
    entities = [EarningEntity::class, CityEntity::class, EarningInputPastEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getEarningsDao(): EarningsDao

    abstract fun getFavoriteCitiesDao(): FavoriteCitiesDao

    abstract fun getEarningInputPastsDao(): EarningInputPastsDao
}