package org.kmp.newsapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import kmp_news_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.data.repository.LocalNewsRepository
import org.kmp.newsapp.theme.detailiImageSize
import org.kmp.newsapp.theme.largePadding
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.ui.common.PulseAnimation
import org.kmp.newsapp.util.getDatabaseBuilder
import org.kmp.newsapp.util.getRoomDataBase
import org.kmp.newsapp.util.shareLink
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetails(
    navController: NavController,
    currentArticle: Article
) {
    var urlHandler = LocalUriHandler.current
    val viewModel = koinViewModel<NewsDetailsViewModel>()

    LaunchedEffect(Unit){
        viewModel.checkIsBookMarked(currentArticle)
    }

    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(mediumPadding)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
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
                        },
                    )

                    val painterState by painter.state.collectAsState()
                    /*val transition by animateFloatAsState(
                        targetValue = if (painterState is AsyncImagePainter.State.Success) {
                            1f
                        } else {
                            0f
                        },
                        animationSpec = tween(durationMillis = 800)
                    )*/

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
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .height(detailiImageSize)
                                    .fillMaxWidth()
                                    .graphicsLayer {
                                        if (result.isSuccess) {
                                            //rotationX = (1f - transition) * 30f
                                            //rotationX = (1f) * 30f
                                            //val scale = 0.8f + (0.2f)
//                                            val scale = 0.8f + (0.2f * transition)
                                            //scaleX = scale
                                            //scaleY = scale
                                        }
                                    }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.height(detailiImageSize).fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,             // End color (fully transparent)
                                        Color.Black.copy() // Start color (more opaque black)
                                    )
                                )
                            )
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(largePadding).align(Alignment.BottomCenter),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            text = currentArticle.title,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            currentArticle.content?.let { content ->
                item {
                    Text(
                        modifier = Modifier.padding(mediumPadding),
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            currentArticle.publishedAt.let { content ->
                item {
                    Text(
                        modifier = Modifier.padding(start = mediumPadding),
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        //Top bar
        Box {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Set the container color to transparent
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        shareLink(currentArticle.url)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        urlHandler.openUri(currentArticle.url)
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_web),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        viewModel.bookmarkArticle(currentArticle)
                    }) {
                        Icon(
                            painter = painterResource(if (viewModel.isBookMarked)
                                Res.drawable.ic_bookmarked else Res.drawable.ic_bookmark
                            ),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
            )
        }
    }
}
