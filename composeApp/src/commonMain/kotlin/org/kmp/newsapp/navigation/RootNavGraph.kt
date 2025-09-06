package org.kmp.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.ui.MainScreen
import org.kmp.newsapp.ui.detail.NewsDetails
import org.kmp.newsapp.ui.setting.SettingScreen
import org.kmp.newsapp.ui.setting.SettingsViewModel
import org.kmp.newsapp.util.articles

@Composable
fun RootNavGraph(
     settingsViewModel: SettingsViewModel
) {
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
            SettingScreen(navController,settingsViewModel)
        }

        composable(
            route = NewsRouteScreen.NewsDetails.route
        ) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let {
                val article= Json.decodeFromString<Article>(it)
                NewsDetails(
                    navController = navController,
                    currentArticle = article
                )
            }
        }
    }
}
