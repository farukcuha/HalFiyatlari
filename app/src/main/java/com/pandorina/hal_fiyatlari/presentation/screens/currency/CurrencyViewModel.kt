package com.pandorina.hal_fiyatlari.presentation.screens.currency

import com.pandorina.hal_fiyatlari.core.BaseViewModel
import com.pandorina.hal_fiyatlari.domain.repository.CurrencyRepository
import com.pandorina.hal_fiyatlari.util.InterstitialAdManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
): BaseViewModel<CurrencyUiState>(CurrencyUiState()) {

    init {
        InterstitialAdManager.show()
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