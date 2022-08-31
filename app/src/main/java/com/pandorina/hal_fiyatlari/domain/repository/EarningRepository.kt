package com.pandorina.hal_fiyatlari.domain.repository

import androidx.room.*
import com.pandorina.hal_fiyatlari.data.local.EarningsDao
import com.pandorina.hal_fiyatlari.data.local.entity.EarningEntity
import kotlinx.coroutines.flow.Flow

interface EarningRepository {
    suspend fun insertEarning(earningEntity: EarningEntity)

    suspend fun deleteEarning(earningId: Int)

    fun getPrices(): Flow<List<EarningEntity>>

    fun getDailySum(startOfDay: Long = EarningsDao.getStartOfDay): Flow<Float?>

    fun getWeeklySum(startOfWeek: Long = EarningsDao.getStartOfWeek): Flow<Float?>

    fun getMonthlySum(startOfMonth: Long = EarningsDao.getStartOfMonth): Flow<Float?>

    fun getTotalSum(): Flow<Float?>
}