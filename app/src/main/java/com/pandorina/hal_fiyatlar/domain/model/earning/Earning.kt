package com.pandorina.hal_fiyatlar.domain.model.earning

import java.text.SimpleDateFormat
import java.util.*

data class Earning(
    val id: Int? = null,
    val name: String,
    val unitPrice: Float,
    val totalWeight: Float,
    val commissionPercentage: Float,
    val totalIncome: Float,
    val timeStamp: Long,
) {
    val date get() = Date(timeStamp).run {
        val oneDay = 86_400_000
        val today = System.currentTimeMillis()
        val remainedTime = today - timeStamp
        val dateFormat = SimpleDateFormat("dd MMMM yyyy EEEE", Locale.getDefault())
        when {
            remainedTime < 0 -> {
                dateFormat.format(this)
            }
            remainedTime < oneDay -> {
                "Bugün"
            }
            (oneDay < remainedTime) && (remainedTime < oneDay * 2) -> {
                "Dün"
            }
            else -> dateFormat.format(this)
        }
    }
}
