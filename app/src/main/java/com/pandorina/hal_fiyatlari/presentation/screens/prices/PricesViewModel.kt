package com.pandorina.hal_fiyatlari.presentation.screens.prices

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.pandorina.hal_fiyatlari.core.BaseViewModel
import com.pandorina.hal_fiyatlari.domain.repository.PricesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PricesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pricesRepository: PricesRepository
): BaseViewModel<PricesUiState>(PricesUiState()){
    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _cityId = mutableStateOf("")
    val cityId: State<String> = _cityId

    init {
        savedStateHandle.get<String>("cityId")?.let {
            _cityId.value = it
            getDates(cityId.value)
        }
        savedStateHandle.get<String>("title")?.let {
            _title.value = it
        }
    }

    private fun getDates(cityId: String?) {
        launchViewModelScope {
            _uiState.value = PricesUiState(isLoading = true)
            pricesRepository.getPriceDates(cityId).collectLatest { result ->
                result.onSuccess { dates ->
                    _uiState.value = _uiState.value.copy(isLoading = false, dates = dates)
                    getPrices(cityId, uiState.value.dates?.firstOrNull())
                }
                result.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }

    fun getPrices(cityId: String?, date: String?){
        job = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, prices = emptyList())
            pricesRepository.getPricesByDate(cityId, date).collectLatest { result ->
                result.onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false, prices = it.prices)
                }
                result.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }
}