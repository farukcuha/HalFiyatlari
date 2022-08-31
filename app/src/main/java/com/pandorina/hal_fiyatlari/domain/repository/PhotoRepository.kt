package com.pandorina.hal_fiyatlari.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.pandorina.hal_fiyatlari.data.remote.photo.dto.PhotoDto
import io.ktor.client.statement.*

interface PhotoRepository {

    suspend fun getHomeScreenPhoto(): HttpResponse
}