package org.kmp.newsapp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.kmp.newsapp.navigation.NavGraph
import org.kmp.newsapp.navigation.NavigationItem
import org.kmp.newsapp.navigation.NavigationSideBar
import org.kmp.newsapp.navigation.NewsBottomNavigation
import org.kmp.newsapp.ui.setting.SettingsViewModel
import org.kmp.newsapp.util.NavigationList

//dfad7bcf78054ff6b41d531a2f83838a
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(settingViewModel: SettingsViewModel) {
    val windowSizeClass = calculateWindowSizeClass()
    val isMediumExpandedWWSC by remember(windowSizeClass) {
        derivedStateOf {
            windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
        }
    }
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
    val navigationItem by remember {
        derivedStateOf {
            NavigationList.find {
                it.route::class.qualifiedName == currentRoute
            }
        }
    }
    val isMainScreenVisible by remember(isMediumExpandedWWSC) {
        derivedStateOf {
            navigationItem != null
        }
    }
    val isBottomBarVisible by remember(isMediumExpandedWWSC) {
        derivedStateOf {
            if (!isMediumExpandedWWSC) {
                navigationItem != null
            } else {
                false
            }
        }
    }
    MainScaffold(
        rootNavController = rootNavController,
        settingViewModel = settingViewModel,
        currentRoute = currentRoute,
        isMediumExpandedWWSC = isMediumExpandedWWSC,
        isBottomBarVisible = isBottomBarVisible,
        isMainScreenVisible = isMainScreenVisible,
        onItemClick = { currentNavigationItem ->
            rootNavController.navigate(currentNavigationItem.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                rootNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                    // Pop up to the start destination, clearing the back stack
                    popUpTo(startDestinationRoute) {
                        // Save the state of popped destinations
                        saveState = true
                    }
                }

                // Configure navigation to avoid multiple instances of the same destination
                launchSingleTop = true

                // Restore state when re-selecting a previously selected item
                restoreState = true
            }
        })
}

@Composable
fun MainScaffold(
    rootNavController: NavHostController,
    settingViewModel: SettingsViewModel,
    currentRoute: String?,
    isMediumExpandedWWSC: Boolean,
    isBottomBarVisible: Boolean,
    isMainScreenVisible: Boolean,
    onItemClick: (NavigationItem) -> Unit,
) {
    Row {
        AnimatedVisibility(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            visible = isMediumExpandedWWSC && isMainScreenVisible,
            enter = slideInHorizontally(
                // Slide in from the left
                initialOffsetX = { fullWidth -> -fullWidth }
            ),
            exit = slideOutHorizontally(
                // Slide out to the right
                targetOffsetX = { fullWidth -> -fullWidth }
            )
        ) {
            NavigationSideBar(
                items = NavigationList,
                currentRoute = currentRoute,
                onItemClick = { currentNavigationItem ->
                    onItemClick(currentNavigationItem)
                }
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(
                        // Slide in from the bottom
                        initialOffsetY = { fullHeight -> fullHeight }
                    ),
                    exit = slideOutVertically(
                        // Slide out to the bottom
                        targetOffsetY = { fullHeight -> fullHeight }
                    )
                ) {
                    NewsBottomNavigation(
                        items = NavigationList,
                        currentRoute = currentRoute,
                        onItemClick = { currentNavigationItem ->
                            onItemClick(currentNavigationItem)
                        }
                    )
                }
            },
            modifier = Modifier.systemBarsPadding()
        ) { innerPadding ->
            NavGraph(
                rootNavController = rootNavController,
                innerPadding = innerPadding,
                settingViewModel = settingViewModel
            )
        }
    }
}