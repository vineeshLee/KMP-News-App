package org.kmp.newsapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.json.Json
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.ui.bookmark.BookMarkScreen
import org.kmp.newsapp.ui.detail.NewsDetailsScreen
import org.kmp.newsapp.ui.headline.HeadLineScreen
import org.kmp.newsapp.ui.search.SearchScreen
import org.kmp.newsapp.ui.setting.SettingScreen
import org.kmp.newsapp.ui.setting.SettingsViewModel

@Composable
fun NavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingsViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Route.Headline,
    ) {
        composable<Route.Headline> {
            HeadLineScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Search> {
            SearchScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.Bookmark> {
            BookMarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }
        composable<Route.NewsDetail> {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let { article ->
                val currentArticle: Article = Json.decodeFromString(article)
                NewsDetailsScreen(rootNavController, currentArticle)
            }
        }
        composable<Route.SettingDetail> {
            SettingScreen(navController = rootNavController, settingViewModel)
        }
    }
}