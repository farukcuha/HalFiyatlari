package com.pandorina.hal_fiyatlari.presentation.screens.earnings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
): ViewModel() {

    private var _earningsState = mutableStateOf<List<Earning>>(emptyList())
    val earningsState: State<List<Earning>> = _earningsState

    private var _dailySumState = mutableStateOf<Float?>(null)
    val dailySumState: State<Float?> = _dailySumState

    private var _weeklySumState = mutableStateOf<Float?>(null)
    val weeklySumState: State<Float?> = _weeklySumState

    private var _monthlySumState = mutableStateOf<Float?>(null)
    val monthlySumState: State<Float?> = _monthlySumState

    private var _totalSumState = mutableStateOf<Float?>(null)
    val totalSumState: State<Float?> = _totalSumState

    init {
        getPrices()
        getDailySum()
        getWeeklySum()
        getMonthlySum()
        getTotalSum()
    }

    fun getPrices() {
        viewModelScope.launch {
            earningRepository.getPrices().collectLatest {
                _earningsState.value = it.map { earningEntity ->
                    earningEntity.toEarning()
                }
            }
        }
    }

    fun getDailySum() {
        viewModelScope.launch {
            earningRepository.getDailySum().collectLatest {
                _dailySumState.value = it
            }
        }
    }

    private fun getWeeklySum(){
        viewModelScope.launch {
            earningRepository.getWeeklySum().collectLatest {
                _weeklySumState.value = it
            }
        }
    }

    private fun getMonthlySum(){
        viewModelScope.launch {
            earningRepository.getMonthlySum().collectLatest {
                _monthlySumState.value = it
            }
        }
    }

    private fun getTotalSum() {
        viewModelScope.launch {
            earningRepository.getTotalSum().collectLatest {
                _totalSumState.value = it
            }
        }
    }

    fun deleteEarning(earningId: Int){
        viewModelScope.launch {
            earningRepository.deleteEarning(earningId)
        }
    }
}