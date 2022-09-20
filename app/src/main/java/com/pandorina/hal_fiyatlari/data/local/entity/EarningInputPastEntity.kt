package com.pandorina.hal_fiyatlari.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "earning_input_past_table", primaryKeys = ["name"])
data class EarningInputPastEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "field_id")
    val fieldId: Int,
    @ColumnInfo(name = "time")
    val timeStamp: Long
) {

    enum class Field{
        NAME, UNIT_PRICE, TOTAL_CASE_COUNT, CASE_WEIGHT, COMMISSION_PERCENTAGE
    }
}

