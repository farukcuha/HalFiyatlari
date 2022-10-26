package com.pandorina.hal_fiyatlar.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<T: BaseUiState>(uiState: T): ViewModel() {
    var job: Job? = null

    protected val _uiState =  mutableStateOf(uiState)
    val uiState: State<T> = _uiState

    fun launchViewModelScope(process: suspend () -> Unit){
        job = viewModelScope.launch { process() }
    }

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}