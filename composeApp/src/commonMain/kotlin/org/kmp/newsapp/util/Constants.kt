package org.kmp.newsapp.util

import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark
import kmp_news_app.composeapp.generated.resources.ic_home
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.search
import org.kmp.newsapp.navigation.BottomNavigationItem

enum class Type{
    MOBILE,
    DESKTOP
}

val bottomBarList=listOf<BottomNavigationItem>(
    BottomNavigationItem(
        icon = Res.drawable.ic_home,
        title = Res.string.headlines,
        route = "HeadLine"
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = "Search"
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark,
        title = Res.string.bookmark,
        route = "BookMark"
    )
)