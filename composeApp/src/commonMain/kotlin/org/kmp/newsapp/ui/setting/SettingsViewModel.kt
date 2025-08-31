package org.kmp.newsapp.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kmp.newsapp.util.AppPreferences

class SettingsViewModel(val appPreferences: AppPreferences) : ViewModel() {
    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()

    init {
        currentThemeGet()
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.update {
            appPreferences.getTheme()
        }
    }

    fun changeThemeMode(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeThemeMode(value)
            _currentTheme.update {
                value
            }
        }
    }
}