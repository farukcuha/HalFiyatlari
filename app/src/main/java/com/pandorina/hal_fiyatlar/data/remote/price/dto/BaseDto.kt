package com.pandorina.hal_fiyatlar.data.remote.price.dto

abstract class BaseDto<T>{
    abstract fun toDomain(): T
}