package com.pandorina.hal_fiyatlar.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String{
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return dateFormat.format(Date(this))
}