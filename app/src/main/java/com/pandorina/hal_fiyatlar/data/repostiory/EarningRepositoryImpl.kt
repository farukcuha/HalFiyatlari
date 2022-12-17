package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.local.EarningsDao
import com.pandorina.hal_fiyatlar.data.local.entity.EarningEntity
import com.pandorina.hal_fiyatlar.domain.repository.EarningRepository
import kotlinx.coroutines.flow.Flow

class EarningRepositoryImpl(
    private val earningsDao: EarningsDao
): EarningRepository {
    override suspend fun insertEarning(earningEntity: EarningEntity) {
        return earningsDao.insertEarning(earningEntity)
    }

    override suspend fun deleteEarning(earningId: Int) {
        return earningsDao.deleteEarning(earningId)
    }

    override suspend fun clear() {
        earningsDao.clear()
    }

    override fun getPrices(): Flow<List<EarningEntity>> {
        return earningsDao.getPrices()
    }

    override fun getDailySum(startOfDay: Long): Flow<Float?> {
        return earningsDao.getDailySum()
    }

    /*override fun getWeeklySum(startOfWeek: Long): Flow<Float?> {
        return earningsDao.getWeeklySum()
    }*/

    override fun getMonthlySum(startOfMonth: Long): Flow<Float?> {
        return earningsDao.getMonthlySum()
    }

    override fun getTotalSum(): Flow<Float?> {
        return earningsDao.getTotalSum()
    }

}