package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.local.dao.EarningInputPastsDao
import com.pandorina.hal_fiyatlar.data.local.entity.EarningInputPastEntity
import com.pandorina.hal_fiyatlar.domain.repository.EarningInputPastsRepository
import kotlinx.coroutines.flow.Flow

class EarningInputPastsRepositoryImpl(
    private val earningInputPastsDao: EarningInputPastsDao
): EarningInputPastsRepository {

    override suspend fun insertEarningInputPast(earningInputPastEntity: EarningInputPastEntity) {
        return earningInputPastsDao.insertEarningInputPast(earningInputPastEntity)
    }

    override suspend fun deleteEarningInputPasts(fieldId: Int) {
        return earningInputPastsDao.deleteEarningInputPasts(fieldId)
    }

    override fun getEarningInputPasts(fieldId: Int): Flow<List<EarningInputPastEntity>> {
        return earningInputPastsDao.getEarningInputPasts(fieldId)
    }
}