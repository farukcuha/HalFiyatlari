package com.pandorina.hal_fiyatlari.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.pandorina.hal_fiyatlari.data.remote.photo.dto.PhotoDto
import com.pandorina.hal_fiyatlari.domain.model.photo.Photo
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

        suspend fun getHomeScreenPhoto(): Flow<Result<Photo>>
}