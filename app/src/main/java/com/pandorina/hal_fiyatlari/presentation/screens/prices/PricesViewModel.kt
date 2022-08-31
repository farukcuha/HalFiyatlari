package com.pandorina.hal_fiyatlari.presentation.screens.prices

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.pandorina.hal_fiyatlari.core.BaseViewModel
import com.pandorina.hal_fiyatlari.domain.repository.PricesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PricesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val pricesRepository: PricesRepository
): BaseViewModel(){
    private val _pricesUiState = mutableStateOf(PricesUiState())
    val pricesUiState: State<PricesUiState> = _pricesUiState

    private val _datesUiState = mutableStateOf(DatesUiState())
    val datesUiState: State<DatesUiState> = _datesUiState

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
        job = viewModelScope.launch {
            _datesUiState.value = DatesUiState(isLoading = true)
            pricesRepository.getPriceDates(cityId).onSuccess {
                val dates = it.map { it.date }
                _datesUiState.value = DatesUiState(isLoading = false, dates = dates)
                getPrices(cityId, datesUiState.value.dates?.firstOrNull())
            }.onFailure {
                _datesUiState.value = DatesUiState(isLoading = false, error = it.localizedMessage)
            }
        }
    }

    fun getPrices(cityId: String?, date: String?){
        job = viewModelScope.launch {
            _pricesUiState.value = PricesUiState(isLoading = true)
            pricesRepository.getPricesByDate(cityId, date).onSuccess {
                val prices = it.map {
                    it.toDomain()
                }
                _pricesUiState.value = PricesUiState(isLoading = false, prices = prices)
            }.onFailure {
                _pricesUiState.value = PricesUiState(isLoading = false, error = it.localizedMessage)
            }
        }
    }
}