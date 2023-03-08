package com.pandorina.hal_fiyatlar.data.local.dao

import androidx.room.*
import com.pandorina.hal_fiyatlar.data.local.entity.EarningEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface EarningsDao {

    companion object {

        val getStartOfDay = Calendar.getInstance(TimeZone.getDefault()).timeInMillis.run {
            this - (this % 86_400_000)
        }

        val getStartOfMonth = Calendar.getInstance(TimeZone.getDefault()).run {
            set(Calendar.DAY_OF_MONTH, 1)
            timeInMillis
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarning(earningEntity: EarningEntity)

    @Query("DELETE FROM earnings_table WHERE id = :earningId")
    suspend fun deleteEarning(earningId: Int)

    @Query("DELETE FROM earnings_table")
    suspend fun clear()

    @Query("SELECT * FROM earnings_table ORDER BY time DESC")
    fun getPrices(): Flow<List<EarningEntity>>

    @Query("SELECT sum(total_income) FROM earnings_table WHERE time > :startOfDay")
    fun getDailySum(startOfDay: Long = getStartOfDay): Flow<Float?>

    @Query("SELECT sum(total_income) FROM earnings_table WHERE time > :startOfMonth")
    fun getMonthlySum(startOfMonth: Long = getStartOfMonth): Flow<Float?>

    @Query("SELECT sum(total_income) FROM earnings_table")
    fun getTotalSum(): Flow<Float?>
}