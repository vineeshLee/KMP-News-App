package org.kmp.newsapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.app_name
import kmp_news_app.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.ui.MainScreen
import org.kmp.newsapp.util.getType

@Composable
@Preview
fun App() {
    //NewsAppTheme(true) {
        MainScreen()
    //}
}