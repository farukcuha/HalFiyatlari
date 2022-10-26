package com.pandorina.hal_fiyatlar.presentation.screens.earnings

import com.pandorina.hal_fiyatlar.core.BaseViewModel
import com.pandorina.hal_fiyatlar.domain.repository.EarningRepository
import com.pandorina.hal_fiyatlar.util.InterstitialAdManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class EarningsViewModel @Inject constructor(
    private val earningRepository: EarningRepository
): BaseViewModel<EarningsUiState>(EarningsUiState()) {

    init {
        InterstitialAdManager.show()
        _uiState.value = EarningsUiState(isLoading = true)
        getPrices()
        getDailySum()
        getWeeklySum()
        getMonthlySum()
        getTotalSum()
    }

    fun getPrices() {
        launchViewModelScope {
            earningRepository.getPrices().collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    earnings = it.map { earningEntity ->
                        earningEntity.toEarning()
                    }
                )
            }
        }
    }

    private fun getDailySum() {
        launchViewModelScope {
            earningRepository.getDailySum().collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    dailySum = it
                )
            }
        }
    }

    private fun getWeeklySum(){
        launchViewModelScope {
            earningRepository.getWeeklySum().collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    weeklySum = it
                )
            }
        }
    }

    private fun getMonthlySum(){
        launchViewModelScope {
            earningRepository.getMonthlySum().collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    monthlySum = it
                )
            }
        }
    }

    private fun getTotalSum() {
        launchViewModelScope {
            earningRepository.getTotalSum().collectLatest {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    totalSum = it
                )
            }
        }
    }

    fun deleteEarning(earningId: Int){
        launchViewModelScope {
            earningRepository.deleteEarning(earningId)
        }
    }

    fun clear(){
        launchViewModelScope {
            earningRepository.clear()
        }
    }
}