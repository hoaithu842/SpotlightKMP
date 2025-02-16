package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.hoaithu842.spotlight_kmp.ui.screen.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) =
    navigate(route = HomeRoute, navOptions = navOptions)


fun NavGraphBuilder.homeScreen(
    onAvatarClick: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            onAvatarClick = onAvatarClick,
        )
    }
}