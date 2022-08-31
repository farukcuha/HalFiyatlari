package com.pandorina.hal_fiyatlari.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {
    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job = null
    }
}