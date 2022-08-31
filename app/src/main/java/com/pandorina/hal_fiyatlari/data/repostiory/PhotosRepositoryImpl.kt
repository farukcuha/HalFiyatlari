package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.remote.photo.PhotoService
import com.pandorina.hal_fiyatlari.domain.repository.PhotoRepository
import io.ktor.client.statement.*

class PhotosRepositoryImpl(
    private val photoService: PhotoService
): PhotoRepository {

    override suspend fun getHomeScreenPhoto(): HttpResponse {
        return photoService.getHomeScreenPhoto()
    }
}