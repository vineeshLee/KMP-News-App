package org.kmp.newsapp.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark
import kmp_news_app.composeapp.generated.resources.ic_home
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.light_mode
import kmp_news_app.composeapp.generated.resources.search
import kmp_news_app.composeapp.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.data.model.Source
import org.kmp.newsapp.navigation.BottomNavigationItem
import org.kmp.newsapp.navigation.MainNavigation
import kotlin.random.Random

const val preferenceFileName="NewsApp.preferences_pb"
const val BASE_URL = "https://newsapi.org/v2/"
const val DB_NAME = "News_DB"
enum class Type{
    MOBILE,
    DESKTOP
}

val bottomBarList=listOf<BottomNavigationItem>(
    BottomNavigationItem(
        icon = Res.drawable.ic_home,
        title = Res.string.headlines,
        route = MainNavigation.HeadLine.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = MainNavigation.Search.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark,
        title = Res.string.bookmark,
        route = MainNavigation.BookMark.route
    )
)

val articles: List<Article> = listOf(
    Article(
        source = Source("dwa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://thrivenews.co/gavin-newsoms-california-dream-decline-and-political-gamesmanship/",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dawdwa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://thrivenews.co/gavin-newsoms-california-dream-decline-and-political-gamesmanship/",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dwakjyk", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://thrivenews.co/gavin-newsoms-california-dream-decline-and-political-gamesmanship/",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    Article(
        source = Source("dwserfewa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "https://thrivenews.co/gavin-newsoms-california-dream-decline-and-political-gamesmanship/",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    )
)

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

val categoryList = arrayListOf(
    "Business",
    "Entertainment",
    "General",
    "Health",
    "Science",
    "Sports",
    "Technology"
)

const val API_KEY = "dfad7bcf78054ff6b41d531a2f83838a"