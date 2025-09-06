@file:Suppress("EQUALITY_NOT_APPLICABLE_WARNING")

package org.kmp.newsapp.ui.headline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_document
import kmp_news_app.composeapp.generated.resources.ic_no_internet
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.theme.xSmallPadding
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect
import org.kmp.newsapp.util.categoryList
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HeadLineScreen(navController: NavController) {
    val viewModel = koinViewModel<HeadLineViewModel>()

    val uiState by viewModel.newsState.collectAsState()
    Column {

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
                    ArticleListScreen(data, navController = navController)
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