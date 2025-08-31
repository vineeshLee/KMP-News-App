package org.kmp.newsapp

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.kmp.newsapp.navigation.RootNavGraph
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.ui.setting.SettingsViewModel
import org.kmp.newsapp.util.AppPreferences
import org.kmp.newsapp.util.dataStorePreference

@Composable
@Preview
fun App() {
    val appPreference = remember {
        AppPreferences(dataStorePreference())
    }
    val settingViewModel= viewModel { SettingsViewModel(appPreference) }
    val currentThem by settingViewModel.currentTheme.collectAsState()

    NewsAppTheme(currentThem) {
        RootNavGraph(settingViewModel)
    }
}