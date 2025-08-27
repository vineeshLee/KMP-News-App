package org.kmp.newsapp.util

import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark
import kmp_news_app.composeapp.generated.resources.ic_home
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.search
import org.kmp.newsapp.navigation.BottomNavigationItem
import org.kmp.newsapp.navigation.MainNavigation

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