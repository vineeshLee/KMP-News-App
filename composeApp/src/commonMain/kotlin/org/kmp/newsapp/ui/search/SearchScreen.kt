package org.kmp.newsapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kmp_news_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.xxSmallPadding
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect

@Composable
fun SearchScreen() {
    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }
    val viewModel = viewModel {
        SearchViewModel()
    }
    val uiState by viewModel.newsState.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
        SearchBar(
            text = searchQuery,
            onValueChange = { changeValue ->
                searchQuery = changeValue
            },
            onSearch = {
                if (it.trim().isNotEmpty()) {
                    viewModel.performSearch()
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
                        onRetryClick = {
                            //Todo retry
                        }
                    )
                } else {
                    ArticleListScreen(data)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_no_internet,
                    onRetryClick = {
                        //Todo retry
                    }
                )
            }
        )
    }

}