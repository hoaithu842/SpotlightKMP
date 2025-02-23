package io.github.hoaithu842.spotlight_kmp.navigation

import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightIcons
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.home
import spotlight.composeapp.generated.resources.library
import spotlight.composeapp.generated.resources.premium
import spotlight.composeapp.generated.resources.search
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val title: StringResource,
    val route: KClass<*>,
) {
    HOME(
        unselectedIcon = SpotlightIcons.Home,
        selectedIcon = SpotlightIcons.HomeSelected,
        title = Res.string.home,
        route = HomeRoute::class,
    ),
    SEARCH(
        unselectedIcon = SpotlightIcons.Search,
        selectedIcon = SpotlightIcons.SearchSelected,
        title = Res.string.search,
        route = SearchRoute::class,
    ),
    LIBRARY(
        unselectedIcon = SpotlightIcons.Library,
        selectedIcon = SpotlightIcons.LibrarySelected,
        title = Res.string.library,
        route = LibraryRoute::class,
    ),
    PREMIUM(
        unselectedIcon = SpotlightIcons.Premium,
        selectedIcon = SpotlightIcons.Premium,
        title = Res.string.premium,
        route = PremiumRoute::class,
    ),
}