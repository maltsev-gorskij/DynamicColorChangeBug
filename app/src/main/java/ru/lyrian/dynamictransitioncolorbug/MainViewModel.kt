package ru.lyrian.dynamictransitioncolorbug

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _isDarkTransitionBackground = MutableStateFlow(false)
    val isDarkTransitionBackground = _isDarkTransitionBackground.asStateFlow()

    fun setDarkTransitionBackground() {
        _isDarkTransitionBackground.update {
            true
        }
    }
}