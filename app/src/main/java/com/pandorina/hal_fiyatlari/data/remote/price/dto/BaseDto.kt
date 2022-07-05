package com.pandorina.hal_fiyatlari.data.remote.price.dto

abstract class BaseDto<T>{
    abstract fun toDomain(): T
}