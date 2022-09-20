package com.pandorina.hal_fiyatlari.presentation.screens.earnings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlari.core.BaseUiState
import com.pandorina.hal_fiyatlari.core.BaseViewModel
import com.pandorina.hal_fiyatlari.data.local.EarningsDao
import com.pandorina.hal_fiyatlari.domain.model.earning.Earning
import com.pandorina.hal_fiyatlari.domain.repository.EarningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarningsViewModel @Inject constructor(
    private val earningRepository: EarningRepository
): BaseViewModel<EarningsUiState>(EarningsUiState()) {

    init {
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
}