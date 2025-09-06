package org.kmp.newsapp

import androidx.compose.ui.window.ComposeUIViewController
import org.kmp.newsapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }