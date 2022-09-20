package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.data.local.entity.EarningInputPastEntity
import kotlinx.coroutines.flow.Flow

interface EarningInputPastsRepository {

    suspend fun insertEarningInputPast(earningInputPastEntity: EarningInputPastEntity)

    suspend fun deleteEarningInputPasts(fieldId: Int)

    fun getEarningInputPasts(fieldId: Int): Flow<List<EarningInputPastEntity>>
}