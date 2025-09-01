package org.kmp.newsapp.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.theme.imageSize
import org.kmp.newsapp.theme.xSmallPadding
import org.kmp.newsapp.theme.xxSmallPadding
import org.kmp.newsapp.util.articles

@Composable
fun ArticleItem(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(5),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding)
        ) {
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(10)),
                contentAlignment = Alignment.Center
            ) {
                 var imageLoadResult by remember {
                     mutableStateOf<Result<Painter>?>(null)
                 }
                 val painter = rememberAsyncImagePainter(
                     model = article.urlToImage,
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
                             contentDescription = article.title,
                             contentScale = ContentScale.Crop,
                             modifier = Modifier
                                 .fillMaxSize()
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
            Column(
                modifier = Modifier.weight(1f).padding(xSmallPadding),
                verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                article.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                }

                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview()
@Composable()
fun HeadLineScree() {
    /*NewsAppTheme {
        articles.forEach { article ->
            ArticleItem(article, onClick = {
            })
        }
    }*/
}