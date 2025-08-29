package org.kmp.newsapp.ui.setting

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.ui.setting.components.ChangeThemeDialog
import org.kmp.newsapp.ui.setting.components.DeleteDialog
import org.kmp.newsapp.ui.setting.components.SettingItem
import org.kmp.newsapp.util.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    var showDeleteBookMarkDialog by remember {
        mutableStateOf(false)
    }

    var showThemeSelection by remember {
        mutableStateOf(false)
    }

    when {
        showThemeSelection -> {
            ChangeThemeDialog(
                currentTheme = Theme.LIGHT_MODE.name,
                onThemeChange = {
                    showThemeSelection = false
                },
                onDismissRequest = {
                showThemeSelection = false
                }

            )
        }

        showDeleteBookMarkDialog -> {
            DeleteDialog(
                onDelete = {
                    showDeleteBookMarkDialog = false
                },
                onDismiss = {
                    showDeleteBookMarkDialog = false
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.setting))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                paddingValues
                SettingItem(
                    onclick = {
                        showThemeSelection = true
                    },
                    painter = painterResource(Res.drawable.ic_light),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                paddingValues
                SettingItem(
                    onclick = {
                        showDeleteBookMarkDialog = true
                    },
                    painter = painterResource(Res.drawable.ice_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
        }

    }
}

@Preview
@Composable
fun Setting() {
    NewsAppTheme(false) {
        //SettingScreen()
    }
}