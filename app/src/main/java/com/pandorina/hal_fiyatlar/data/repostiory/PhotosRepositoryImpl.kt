package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.remote.photo.PhotoService
import com.pandorina.hal_fiyatlar.data.remote.photo.dto.PhotoDto
import com.pandorina.hal_fiyatlar.domain.model.photo.Photo
import com.pandorina.hal_fiyatlar.domain.repository.PhotoRepository
import com.pandorina.hal_fiyatlar.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class PhotosRepositoryImpl(
    private val photoService: PhotoService
): PhotoRepository {

    override suspend fun getHomeScreenPhoto(): Flow<Result<Photo>> = channelFlow {
        safeApiCall<PhotoDto> {
            photoService.getHomeScreenPhoto().call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success<Photo>(
                    it.toDomain()
                ))
            }.onFailure {
                trySend(Result.failure<Photo>(it))
            }
        }
    }
}