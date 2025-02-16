package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.hoaithu842.spotlight_kmp.ui.screen.LibraryScreen
import kotlinx.serialization.Serializable

@Serializable
data object LibraryRoute

fun NavController.navigateToLibraryScreen(navOptions: NavOptions? = null) =
    navigate(route = LibraryRoute, navOptions = navOptions)

fun NavGraphBuilder.libraryScreen() {
    composable<LibraryRoute> {
        LibraryScreen()
    }
}