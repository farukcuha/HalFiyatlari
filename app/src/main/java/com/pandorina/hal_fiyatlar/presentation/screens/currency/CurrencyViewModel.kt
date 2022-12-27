package com.pandorina.hal_fiyatlar.presentation.screens.currency

import com.pandorina.hal_fiyatlar.core.BaseViewModel
import com.pandorina.hal_fiyatlar.domain.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
): BaseViewModel<CurrencyUiState>(CurrencyUiState()) {

    init {
        launchViewModelScope {
            _uiState.value = CurrencyUiState(isLoading = true)
            currencyRepository.getCurrencies().collectLatest { result ->
                result.onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false, currencies = it)
                }.onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }
}