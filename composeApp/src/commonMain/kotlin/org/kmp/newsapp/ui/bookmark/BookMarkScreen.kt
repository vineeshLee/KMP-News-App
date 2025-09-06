package org.kmp.newsapp.ui.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_web
import kmp_news_app.composeapp.generated.resources.no_bookmarks
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.data.repository.LocalNewsRepository
import org.kmp.newsapp.navigation.SettingRoute
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect
import org.kmp.newsapp.util.bottomBarList
import org.kmp.newsapp.util.getDatabaseBuilder
import org.kmp.newsapp.util.getRoomDataBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkScreen(
    paddingValues: PaddingValues,
    rootNavController: NavHostController
) {
    val bookmarkViewModel = viewModel {
        BookMarkViewModel(
            localNewsRepository = LocalNewsRepository(
                getRoomDataBase(getDatabaseBuilder()).newsDao()
            )
        )
    }


    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()
    val originDirection = LocalLayoutDirection.current
    Column(
        modifier = Modifier.fillMaxSize().padding(
            start = paddingValues.calculateStartPadding(originDirection),
            end = paddingValues.calculateEndPadding(originDirection),
            bottom = paddingValues.calculateBottomPadding(),
        ),
    ) {
        uiState.DisplayResult(onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_bookmarks),
                    icon = Res.drawable.ic_web, isOnRetryBtnVisible = false
                )
            } else {
                ArticleListScreen(
                    articleList = articleList,
                    navController = rootNavController
                )
            }
        }, onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_web, onRetryClick = {
                bookmarkViewModel.getArticles()
            })
        })
    }
}