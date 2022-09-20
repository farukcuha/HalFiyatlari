package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.local.EarningInputPastsDao
import com.pandorina.hal_fiyatlari.data.local.entity.EarningInputPastEntity
import com.pandorina.hal_fiyatlari.domain.repository.EarningInputPastsRepository
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