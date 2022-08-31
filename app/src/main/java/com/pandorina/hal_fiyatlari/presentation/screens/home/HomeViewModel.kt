package com.pandorina.hal_fiyatlari.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlari.data.remote.photo.dto.PhotoDto
import com.pandorina.hal_fiyatlari.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _photoUiState = mutableStateOf(HomeUiState())
    val photoUiState: State<HomeUiState> = _photoUiState

    init {
        viewModelScope.launch {
            _photoUiState.value = HomeUiState(isLoading = true)
            /*photoRepository.getHomeScreenPhotos().onSuccess { photos ->
                val random = Random(System.currentTimeMillis())
                val randomIndex = random.nextInt(photos.size)
                val photo = photos[randomIndex].toDomain()
                _photoUiState.value = HomeUiState(photo = photo, isLoading = false)
            }.onFailure {
                _photoUiState.value = HomeUiState(error = it.localizedMessage, isLoading = false)
            }*/
        }
    }
}