package com.pandorina.hal_fiyatlar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pandorina.hal_fiyatlar.domain.model.earning.Earning

@Entity(tableName = "earnings_table")
data class EarningEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "unit_price")
    val unitPrice: Float,
    @ColumnInfo(name = "total_weight")
    val totalWeight: Float,
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
            totalWeight = totalWeight,
            commissionPercentage = commissionPercentage,
            totalIncome = totalIncome,
            timeStamp = timeStamp
        )
    }
}
