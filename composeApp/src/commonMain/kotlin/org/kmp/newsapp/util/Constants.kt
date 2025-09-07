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
import org.kmp.newsapp.navigation.NavigationItem
import org.kmp.newsapp.navigation.Route
import kotlin.random.Random

const val preferenceFileName="NewsApp.preferences_pb"
const val BASE_URL = "https://newsapi.org/v2/"
const val DB_NAME = "News_DB"
enum class Type{
    MOBILE,
    DESKTOP
}

val NavigationList=listOf<NavigationItem>(
    NavigationItem(
        icon = Res.drawable.ic_home,
        title = Res.string.headlines,
        route = Route.Headline
    ),
    NavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = Route.Search
    ),
    NavigationItem(
        icon = Res.drawable.ic_bookmark,
        title = Res.string.bookmark,
        route = Route.Bookmark
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

const val API_KEY = ""