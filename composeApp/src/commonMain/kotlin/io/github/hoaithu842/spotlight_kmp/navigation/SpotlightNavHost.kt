package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.hoaithu842.spotlight_kmp.navigation.HomeRoute
import io.github.hoaithu842.spotlight_kmp.navigation.homeScreen
import io.github.hoaithu842.spotlight_kmp.navigation.libraryScreen
import io.github.hoaithu842.spotlight_kmp.navigation.premiumScreen
import io.github.hoaithu842.spotlight_kmp.navigation.searchScreen

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
        searchScreen()
        libraryScreen()
        premiumScreen()
    }
}