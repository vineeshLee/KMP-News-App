package org.kmp.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.kmp.newsapp.ui.MainScreen
import org.kmp.newsapp.ui.detail.NewsDetails
import org.kmp.newsapp.ui.setting.SettingScreen
import org.kmp.newsapp.util.articles

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.RootNavigation,
        startDestination = Graph.MainNavigation
    ) {
        composable(
            route = Graph.MainNavigation
        ) {
            MainScreen(navController)
        }

        composable(
            route = SettingRoute.Setting.route
        ) {
            SettingScreen(navController)
        }

        composable(
            route = NewsRouteScreen.NewsDetails.route
        ) {
            NewsDetails(
                navController = navController,
                currentArticle = articles[0]
            )
        }
    }
}
