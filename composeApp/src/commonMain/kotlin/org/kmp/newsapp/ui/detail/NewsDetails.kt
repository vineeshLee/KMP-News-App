package org.kmp.newsapp.ui.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_bookmark
import kmp_news_app.composeapp.generated.resources.ic_logo
import kmp_news_app.composeapp.generated.resources.ic_web
import kmp_news_app.composeapp.generated.resources.news_detail
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.theme.detailiImageSize
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.xLargePadding
import org.kmp.newsapp.ui.common.PulseAnimation
import org.kmp.newsapp.util.shareLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetails(
    navController: NavController,
    currentArticle: Article
) {
    var urlHandler= LocalUriHandler.current

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(Res.string.news_detail),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                IconButton(onClick = {
                    shareLink(currentArticle.url)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = {
                    urlHandler.openUri(currentArticle.url)
                }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_web),
                        contentDescription = null,
                    )
                }
                IconButton(onClick = {
                    //articleDetailViewModel.bookmarkArticle(currentArticle)
                }) {
                    Icon(
                        //if (articleDetailViewModel.isBookmarked)
                        painter = painterResource(
                             Res.drawable.ic_bookmark
                        ),
                        contentDescription = null,
                    )
                }
            },
        )
    }) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = xLargePadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(5)),
                    contentAlignment = Alignment.Center
                ) {
                    var imageLoadResult by remember {
                        mutableStateOf<Result<Painter>?>(null)
                    }
                    val painter = rememberAsyncImagePainter(
                        model = currentArticle.urlToImage,
                        onSuccess = {
                            imageLoadResult =
                                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                    Result.success(it.painter)
                                } else {
                                    Result.failure(Exception("Invalid image size"))
                                }
                        },
                        onError = {
                            it.result.throwable.printStackTrace()
                            imageLoadResult = Result.failure(it.result.throwable)
                        }
                    )

                    val painterState by painter.state.collectAsState()
                    val transition by animateFloatAsState(
                        targetValue = if (painterState is AsyncImagePainter.State.Success) {
                            1f
                        } else {
                            0f
                        },
                        animationSpec = tween(durationMillis = 800)
                    )

                    when (val result = imageLoadResult) {
                        null -> {
                            PulseAnimation(
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        else -> {
                            Image(
                                painter = if (result.isSuccess) painter else {
                                    painterResource(Res.drawable.ic_logo)
                                },
                                contentDescription = currentArticle.title,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .height(detailiImageSize)
                                    .fillMaxWidth()
                                    .graphicsLayer {
                                        if (result.isSuccess) {
                                            rotationX = (1f - transition) * 30f
                                            val scale = 0.8f + (0.2f * transition)
                                            scaleX = scale
                                            scaleY = scale
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = currentArticle.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            currentArticle.description?.let { content ->
                item {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            currentArticle.publishedAt.let { content ->
                item {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

    }

}