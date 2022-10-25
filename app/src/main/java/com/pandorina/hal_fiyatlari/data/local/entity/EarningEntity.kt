package com.pandorina.hal_fiyatlari.data.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandorina.hal_fiyatlari.domain.model.earning.Earning

@Entity(tableName = "earnings_table")
data class EarningEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "unit_price")
    val unitPrice: Float,
    @ColumnInfo(name = "total_case_count")
    val totalCaseCount: Float,
    @ColumnInfo(name = "case_weight")
    val caseWeight: Float,
    @ColumnInfo(name = "commission_percentage")
    val commissionPercentage: Float,
    @ColumnInfo(name = "total_income")
    val totalIncome: Float,
    @ColumnInfo(name = "time")
    val timeStamp: Long
){
    fun toEarning(): Earning {

        return Earning(
            id = id,
            name = name,
            unitPrice = unitPrice,
            totalCaseCount = totalCaseCount,
            caseWeight = caseWeight,
            commissionPercentage = commissionPercentage,
            totalIncome = totalIncome,
            timeStamp = timeStamp
        )
    }
}
