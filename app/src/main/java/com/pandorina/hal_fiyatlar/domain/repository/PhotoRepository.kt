package com.pandorina.hal_fiyatlar.domain.repository

import com.pandorina.hal_fiyatlar.domain.model.photo.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

        suspend fun getHomeScreenPhoto(): Flow<Result<Photo>>
}