package org.kmp.newsapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.kmp.newsapp.ui.bookmark.BookMarkScreen
import org.kmp.newsapp.ui.headline.HeadLineScreen
import org.kmp.newsapp.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        navController = homeNavController,
        route = Graph.MainNavigation,
        startDestination = MainNavigation.HeadLine.route
    ) {
        composable(
            route = MainNavigation.HeadLine.route
        ) {
            HeadLineScreen(rootNavController)
        }
        composable(
            route = MainNavigation.BookMark.route
        ) {
            BookMarkScreen()
        }
        composable(
            route = MainNavigation.Search.route
        ) {
            SearchScreen(rootNavController)
        }
    }
}