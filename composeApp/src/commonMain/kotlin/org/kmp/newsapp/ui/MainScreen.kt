package org.kmp.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.navigation.NewsBottomNavigation
import org.kmp.newsapp.theme.shimmerColors
import org.kmp.newsapp.util.bottomBarList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentBottom by remember { mutableStateOf(bottomBarList[0].route) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "HeadLines",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(resource = Res.string.setting)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NewsBottomNavigation(
                items = bottomBarList,
                currentRoute = currentBottom,
                onItemClick = { currentBottomClickItem ->
                    currentBottom = currentBottomClickItem.route
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Text(text = "Hello world")
        }
    }
}