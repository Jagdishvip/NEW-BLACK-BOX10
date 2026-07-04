package com.vspace.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _injectionProgress = MutableLiveData<Int>()
    val injectionProgress: LiveData<Int> = _injectionProgress

    private val _injectionStatus = MutableLiveData<String>()
    val injectionStatus: LiveData<String> = _injectionStatus

    private val _selectedApp = MutableLiveData<String>()
    val selectedApp: LiveData<String> = _selectedApp

    private val _selectedSoFile = MutableLiveData<String>()
    val selectedSoFile: LiveData<String> = _selectedSoFile

    fun updateProgress(progress: Int) {
        _injectionProgress.value = progress
    }

    fun updateStatus(status: String) {
        _injectionStatus.value = status
    }

    fun setSelectedApp(appName: String) {
        _selectedApp.value = appName
    }

    fun setSelectedSoFile(fileName: String) {
        _selectedSoFile.value = fileName
    }

    fun startInjection() {
        _injectionProgress.value = 0
        _injectionStatus.value = "Starting injection..."
        // Implement injection logic
    }

    fun cancelInjection() {
        _injectionProgress.value = 0
        _injectionStatus.value = "Cancelled"
    }
}
