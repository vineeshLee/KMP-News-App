package org.kmp.newsapp.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.utils.EmptyContent
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_document
import kmp_news_app.composeapp.generated.resources.ic_no_internet
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.ui.common.EmptyContent
import org.kmp.newsapp.ui.common.ShimmerEffect

@Composable
fun HeadLineScreen() {
    val viewModel = viewModel {
        HeadLineViewModel()
    }
    val uiState by viewModel.newsState.collectAsState()
    uiState.DisplayResult(
        onIdle = {
        },
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = {data->
            if (data.isEmpty()){
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_document,
                    onRetryClick = {
                        //Todo retry
                    }
                )
            }else{
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