@file:Suppress("EQUALITY_NOT_APPLICABLE_WARNING")

package org.kmp.newsapp.ui.headline

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.navigation.Route
import org.kmp.newsapp.theme.xSmallPadding
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect
import org.kmp.newsapp.util.NavigationList
import org.kmp.newsapp.util.categoryList
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadLineScreen(rootNavController: NavController, paddingValues: PaddingValues) {
    val viewModel = koinViewModel<HeadLineViewModel>()

    val uiState by viewModel.newsState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(NavigationList[0].title),
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
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding, Alignment.CenterHorizontally)
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    selected = viewModel.category == category,
                    onClick = {
                        viewModel.category = category
                        viewModel.getHeadLines()
                    }, label = {
                        Text(category)
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }

        uiState.DisplayResult(
            onIdle = {
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { data ->
                if (data.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_document,
                        onRetryClick = {
                            viewModel.getHeadLines()
                        }
                    )
                } else {
                    ArticleListScreen(data, navController = rootNavController)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_no_internet,
                    onRetryClick = {
                        viewModel.getHeadLines()
                    },
                    isOnRetryBtnVisible = true
                )
            }
        )
    }

}