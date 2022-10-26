package com.pandorina.hal_fiyatlar.presentation.screens.earnings

import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlar.core.BaseViewModel
import com.pandorina.hal_fiyatlar.data.local.entity.EarningEntity
import com.pandorina.hal_fiyatlar.data.local.entity.EarningInputPastEntity
import com.pandorina.hal_fiyatlar.domain.repository.EarningInputPastsRepository
import com.pandorina.hal_fiyatlar.domain.repository.EarningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEarningViewModel @Inject constructor(
    private val earningRepository: EarningRepository,
    private val earningInputPastsRepository: EarningInputPastsRepository
): BaseViewModel<AddEarningsUiState>(AddEarningsUiState()) {

    init {
        _uiState.value = AddEarningsUiState()
        getInputPasts()
    }

    fun insertEarning(earningEntity: EarningEntity){
        viewModelScope.launch {
            earningRepository.insertEarning(earningEntity)

            insertInputPast(earningEntity.name, EarningInputPastEntity.Field.NAME)
            insertInputPast(earningEntity.unitPrice.toString(), EarningInputPastEntity.Field.UNIT_PRICE)
            insertInputPast(earningEntity.totalCaseCount.toString(), EarningInputPastEntity.Field.TOTAL_CASE_COUNT)
            insertInputPast(earningEntity.caseWeight.toString(), EarningInputPastEntity.Field.CASE_WEIGHT)
            insertInputPast(earningEntity.commissionPercentage.toString(), EarningInputPastEntity.Field.COMMISSION_PERCENTAGE)
        }
    }

    private suspend fun insertInputPast(
        name: String,
        field: EarningInputPastEntity.Field
    ){
        earningInputPastsRepository.insertEarningInputPast(
            EarningInputPastEntity(
                name,
                field.ordinal,
                System.currentTimeMillis()
            )
        )
    }

    fun deleteEarning(earningId: Int){
        viewModelScope.launch {
            earningRepository.deleteEarning(earningId)
        }
    }

    fun deleteInputPasts(field: EarningInputPastEntity.Field){
        viewModelScope.launch {
            earningInputPastsRepository.deleteEarningInputPasts(field.ordinal)
        }
    }

    private fun getInputPasts(){
        viewModelScope.launch {
            getInputPast(EarningInputPastEntity.Field.NAME){
                _uiState.value = _uiState.value.copy(nameInputPasts = it.map { entity ->
                    entity.name
                })
            }
            getInputPast(EarningInputPastEntity.Field.UNIT_PRICE){
                _uiState.value = _uiState.value.copy(unitPriceInputPasts = it.map { entity ->
                    entity.name
                })
            }
            getInputPast(EarningInputPastEntity.Field.TOTAL_CASE_COUNT){
                _uiState.value = _uiState.value.copy(totalCaseCountInputPasts = it.map { entity ->
                    entity.name
                })
            }

            getInputPast(EarningInputPastEntity.Field.CASE_WEIGHT){
                _uiState.value = _uiState.value.copy(caseWeightInputPasts = it.map { entity ->
                    entity.name
                })
            }
            getInputPast(EarningInputPastEntity.Field.COMMISSION_PERCENTAGE){
                _uiState.value = _uiState.value.copy(commissionPercentageInputPasts = it.map { entity ->
                    entity.name
                })
            }
        }
    }

    private fun CoroutineScope.getInputPast(
        field: EarningInputPastEntity.Field,
        onCollect: (List<EarningInputPastEntity>) -> Unit){
        launch {
            earningInputPastsRepository.getEarningInputPasts(field.ordinal).collectLatest {
                onCollect(it)
            }
        }
    }
}