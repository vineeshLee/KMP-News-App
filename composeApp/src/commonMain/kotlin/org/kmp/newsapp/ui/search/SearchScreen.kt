package org.kmp.newsapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.navigation.Route
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.xxSmallPadding
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect
import org.kmp.newsapp.util.NavigationList
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(rootNavController: NavController, paddingValues: PaddingValues) {
    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }
    val viewModel = koinViewModel<SearchViewModel>()

    val uiState by viewModel.newsState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(NavigationList[1].title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                IconButton(onClick = {
                    rootNavController.navigate(Route.SettingDetail)
                }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(resource = Res.string.setting)
                    )
                }
            }
        )

        SearchBar(
            text = searchQuery,
            onValueChange = { changeValue ->
                searchQuery = changeValue
            },
            onSearch = {
                if (it.trim().isNotEmpty()) {
                    viewModel.performSearch(it)
                }
            }
        )

        Spacer(modifier = Modifier.height(xxSmallPadding))

        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_document,
                    onRetryClick = {},
                    isOnRetryBtnVisible = false
                )
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { data ->
                if (data.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_document,
                        onRetryClick = {}
                    )
                } else {
                    ArticleListScreen(data,rootNavController)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_no_internet,
                    onRetryClick = {
                        viewModel.performSearch(searchQuery)
                    },
                    isOnRetryBtnVisible = true
                )
            }
        )
    }

}