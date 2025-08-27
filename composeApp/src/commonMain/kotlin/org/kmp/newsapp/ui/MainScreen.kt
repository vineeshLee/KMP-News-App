package org.kmp.newsapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource
import org.kmp.newsapp.navigation.MainNavGraph
import org.kmp.newsapp.navigation.NewsBottomNavigation
import org.kmp.newsapp.navigation.SettingRoute
import org.kmp.newsapp.util.bottomBarList
//dfad7bcf78054ff6b41d531a2f83838a
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootNavController: NavHostController) {
    val homeNavController = rememberNavController()

    val currentBackStackEntry by homeNavController.currentBackStackEntryAsState()

    var preRoute by rememberSaveable {
        mutableStateOf(currentBackStackEntry?.destination?.route)
    }

    val currentBottom by remember(currentBackStackEntry) {
        derivedStateOf { currentBackStackEntry?.destination?.route }
    }

    val topTitle by remember(currentBottom) {
        derivedStateOf {
            if (currentBottom != null) {
                bottomBarList[bottomBarList.indexOfFirst {
                    it.route == currentBottom
                }].title
            } else {
                bottomBarList[0].title
            }
        }
    }

    DisposableEffect(Unit) {
        preRoute = currentBottom
        onDispose {
            print("prev state:${currentBottom}")
        }
    }

    LaunchedEffect(Unit) {
        if (preRoute != null) {
            homeNavController.navigate(preRoute!!) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                homeNavController.graph.startDestinationRoute?.let {
                    // Pop up to the start destination, clearing the back stack
                    popUpTo(it) {
                        // Save the state of popped destinations
                        saveState = true
                    }
                }
                // Configure navigation to avoid multiple instances of the same destination
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(topTitle),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = {
                        rootNavController.navigate(SettingRoute.Setting.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(resource = Res.string.setting)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NewsBottomNavigation(
                items = bottomBarList,
                currentRoute = currentBottom,
                onItemClick = { currentBottomClickItem ->
                    homeNavController.navigate(currentBottomClickItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        homeNavController.graph.startDestinationRoute?.let {
                            // Pop up to the start destination, clearing the back stack
                            popUpTo(it) {
                                // Save the state of popped destinations
                                saveState = true
                            }
                        }
                        // Configure navigation to avoid multiple instances of the same destination
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        MainNavGraph(
            homeNavController = homeNavController,
            rootNavController = rootNavController,
            paddingValues = paddingValues
        )
    }
}