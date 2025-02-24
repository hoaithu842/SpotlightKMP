package io.github.hoaithu842.spotlight_kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import coil3.annotation.ExperimentalCoilApi
import io.github.hoaithu842.spotlight_kmp.manager.NetworkListener
import io.github.hoaithu842.spotlight_kmp.manager.NetworkStatus
import io.github.hoaithu842.spotlight_kmp.navigation.SpotlightNavHost
import io.github.hoaithu842.spotlight_kmp.navigation.TopLevelDestination
import io.github.hoaithu842.spotlight_kmp.navigation.navigateToHomeScreen
import io.github.hoaithu842.spotlight_kmp.navigation.navigateToLibrary
import io.github.hoaithu842.spotlight_kmp.navigation.navigateToPremiumScreen
import io.github.hoaithu842.spotlight_kmp.navigation.navigateToSearch
import io.github.hoaithu842.spotlight_kmp.presentation.screen.PlayerView
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.HomeScreenDrawer
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightNavigationBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightNavigationBarItem
import io.github.hoaithu842.spotlight_kmp.ui.theme.BlurGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.SpotlightTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import kotlin.reflect.KClass

fun navigateToTopLevelDestination(
    navController: NavHostController,
    topLevelDestination: TopLevelDestination,
) {
    val topLevelNavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    when (topLevelDestination) {
        TopLevelDestination.HOME -> navController.navigateToHomeScreen(
            topLevelNavOptions
        )

        TopLevelDestination.SEARCH -> navController.navigateToSearch(
            topLevelNavOptions
        )

        TopLevelDestination.LIBRARY -> navController.navigateToLibrary(
            topLevelNavOptions
        )

        TopLevelDestination.PREMIUM -> navController.navigateToPremiumScreen(
            topLevelNavOptions
        )
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
@Preview
fun App(
    networkListener: NetworkListener = koinInject(),
) {
    KoinContext {
        val networkStatus by networkListener.networkStatus.collectAsState(initial = NetworkStatus.Connected)
        var isNavBarDisplaying by remember { mutableStateOf(true) }
        val density = LocalDensity.current
        val scaffoldState = rememberBottomSheetScaffoldState()
        val navController = rememberNavController()
        val currentDestination: NavDestination? =
            navController.currentBackStackEntryAsState().value?.destination
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(
            initialPage = 1,
            pageCount = {
                2
            },
        )
        var currentScrollState by remember { mutableStateOf(false) }
        val snackbarHostState = remember { SnackbarHostState() }
        LaunchedEffect(networkStatus) {
            snackbarHostState.showSnackbar(networkStatus.toString())
        }

        LaunchedEffect(pagerState.currentPage) {
            currentScrollState = pagerState.currentPage == 0
        }
        LaunchedEffect(scaffoldState.bottomSheetState.currentValue) {
            if (scaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded) {
                isNavBarDisplaying = true
            }
        }

        SpotlightTheme {
            HorizontalPager(
                state = pagerState,
                contentPadding = if (pagerState.currentPage == 0) PaddingValues(end = 80.dp) else PaddingValues(
                    end = 0.dp
                ),
                userScrollEnabled = currentScrollState,
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
            ) { page ->
                if (page == 0) {
                    HomeScreenDrawer()
                } else {
                    Box(
                        Modifier.fillMaxSize()
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            bottomBar = {
                                AnimatedVisibility(
                                    visible = isNavBarDisplaying,
                                    enter = slideInVertically {
                                        // Slide in from 40 dp from the top.
                                        with(density) { -40.dp.roundToPx() }
                                    } + expandVertically(
                                        // Expand from the top.
                                        expandFrom = Alignment.Top
                                    ) + fadeIn(
                                        // Fade in with the initial alpha of 0.3f.
                                        initialAlpha = 0.3f
                                    ),
                                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                                ) {
                                    SpotlightNavigationBar {
                                        TopLevelDestination.entries.forEach { destination ->
                                            val selected =
                                                currentDestination.isRouteInHierarchy(destination.route)
                                            SpotlightNavigationBarItem(
                                                title = stringResource(destination.title),
                                                selected = selected,
                                                icon = destination.unselectedIcon,
                                                selectedIcon = destination.selectedIcon,
                                                onClick = {
                                                    navigateToTopLevelDestination(
                                                        navController = navController,
                                                        topLevelDestination = destination,
                                                    )
                                                },
                                            )
                                        }
                                    }
                                }
                            },
                        ) { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .padding(bottom = innerPadding.calculateBottomPadding())
                                    .fillMaxSize()
                            ) {
                                BottomSheetScaffold(
                                    scaffoldState = scaffoldState,
                                    sheetPeekHeight = SpotlightDimens.MinimizedPlayerHeight,
                                    sheetShape = RoundedCornerShape(0.dp),
                                    sheetDragHandle = {},
                                    sheetShadowElevation = 0.dp,
                                    sheetContent = {
                                        PlayerView(
                                            scaffoldState = scaffoldState,
                                            navBarDisplayingChange = { isNavBarDisplaying = it },
                                        )
                                    },
                                    snackbarHost = { SnackbarHost(snackbarHostState) },
                                    sheetContainerColor = Color.Transparent,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    SpotlightNavHost(
                                        navHostController = navController,
                                        onAvatarClick = {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(0)
                                            }
                                        },
                                    )
                                }
                            }
                            if (pagerState.currentPage == 0) {
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = BlurGray)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}