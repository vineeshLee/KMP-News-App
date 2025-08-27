package org.kmp.newsapp.navigation

object Graph{
    const val RootNavigation="RootNavigation"
    const val MainNavigation="MainNavigation"
}
sealed class MainNavigation(var route: String){
    object HeadLine: MainNavigation("HeadLine")
    object Search: MainNavigation("Search")
    object BookMark: MainNavigation("BookMark")
}

sealed class SettingRoute(var route: String){
    object Setting: SettingRoute("Setting")
}