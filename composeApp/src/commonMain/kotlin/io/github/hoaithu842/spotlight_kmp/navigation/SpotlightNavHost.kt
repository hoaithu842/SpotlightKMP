package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions

@Composable
fun SpotlightNavHost(
    navHostController: NavHostController,
    onAvatarClick: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeRoute,
    ) {
        homeScreen(onAvatarClick = onAvatarClick)
        searchGraph(
            onAvatarClick = onAvatarClick,
            onCancelClick = navHostController::popBackStack,
            onNavigateToSearchClick = {
                navHostController.navigateToSearchResultScreen(
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            },
        )
        libraryGraph(
            onAvatarClick = onAvatarClick,
            onCancelClick = navHostController::popBackStack,
            onNavigateToSearchClick = {
                navHostController.navigateToLibrarySearchScreen(
                    navOptions = navOptions {
                        launchSingleTop = true
                    }
                )
            },
        )
        premiumScreen()
    }
}