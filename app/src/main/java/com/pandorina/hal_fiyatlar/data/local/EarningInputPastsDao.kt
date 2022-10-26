package com.pandorina.hal_fiyatlar.data.local

import androidx.room.*
import com.pandorina.hal_fiyatlar.data.local.entity.EarningInputPastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EarningInputPastsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarningInputPast(earningInputPastEntity: EarningInputPastEntity)

    @Query("DELETE FROM earning_input_past_table WHERE :field = field_id")
    suspend fun deleteEarningInputPasts(field: Int)

    @Query("SELECT * FROM earning_input_past_table WHERE field_id = :fieldId ORDER BY time DESC")
    fun getEarningInputPasts(fieldId: Int): Flow<List<EarningInputPastEntity>>
}

