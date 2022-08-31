package com.pandorina.hal_fiyatlari.presentation.screens.earnings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlari.data.local.entity.EarningEntity
import com.pandorina.hal_fiyatlari.domain.repository.EarningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEarningViewModel @Inject constructor(
    private val earningRepository: EarningRepository
): ViewModel() {

    fun insertEarning(earningEntity: EarningEntity){
        viewModelScope.launch {
            earningRepository.insertEarning(earningEntity)
        }
    }

    fun deleteEarning(earningId: Int){
        viewModelScope.launch {
            earningRepository.deleteEarning(earningId)
        }
    }
}