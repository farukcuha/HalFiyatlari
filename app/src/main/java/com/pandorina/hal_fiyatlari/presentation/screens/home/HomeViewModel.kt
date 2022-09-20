package com.pandorina.hal_fiyatlari.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.pandorina.hal_fiyatlari.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    companion object{
        const val CONFIG_IS_PRICES_ACTIVE = "is_prices_active"
        const val CONFIG_IS_EARNINGS_ACTIVE = "is_earnings_active"
        const val CONFIG_IS_NEWS_ACTIVE = "is_news_active"
        const val CONFIG_IS_CURRENCY_ACTIVE = "is_currency_active"
        const val CONFIG_INFO_TEXT = "info_text"
    }

    val _photoUiState = mutableStateOf(HomeUiState())
    val photoUiState: State<HomeUiState> = _photoUiState

    init {
        viewModelScope.launch {
            _photoUiState.value = HomeUiState(isLoading = true)
            photoRepository.getHomeScreenPhoto().collectLatest { result ->
                result.onSuccess {
                    _photoUiState.value = _photoUiState.value.copy(isLoading = false, photo = it)
                }.onFailure {
                    _photoUiState.value = _photoUiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
            loadConfigs()
        }
    }

    private fun loadConfigs(){
        _photoUiState.value = _photoUiState.value.copy(isPricesActive =
        Firebase.remoteConfig.getBoolean(CONFIG_IS_PRICES_ACTIVE))

        _photoUiState.value = _photoUiState.value.copy(isEarningsActive =
        Firebase.remoteConfig.getBoolean(CONFIG_IS_EARNINGS_ACTIVE))

        _photoUiState.value = _photoUiState.value.copy(isNewsActive =
        Firebase.remoteConfig.getBoolean(CONFIG_IS_NEWS_ACTIVE))

        _photoUiState.value = _photoUiState.value.copy(isCurrencyActive =
        Firebase.remoteConfig.getBoolean(CONFIG_IS_CURRENCY_ACTIVE))

        _photoUiState.value = _photoUiState.value.copy(infoText =
        Firebase.remoteConfig.getString(CONFIG_INFO_TEXT))

        _photoUiState.value = _photoUiState.value.copy(showDialog =
        _photoUiState.value.infoText.isNotEmpty())
    }
}