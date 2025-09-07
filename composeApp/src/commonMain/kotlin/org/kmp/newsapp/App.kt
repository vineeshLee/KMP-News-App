package org.kmp.newsapp

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.ui.MainScreen
import org.kmp.newsapp.ui.setting.SettingsViewModel
import org.kmp.newsapp.util.AppPreferences
import org.kmp.newsapp.util.dataStorePreference
import org.kmp.newsapp.util.getDatabaseBuilder
import org.kmp.newsapp.util.getRoomDataBase
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val appPreference = remember {
        AppPreferences(dataStorePreference())
    }
    val newsDao=remember { getRoomDataBase(getDatabaseBuilder()).newsDao() }
    val settingViewModel= koinViewModel<SettingsViewModel>()
    val currentThem by settingViewModel.currentTheme.collectAsState()

    NewsAppTheme(currentThem) {
        MainScreen(settingViewModel=settingViewModel)
    }
}