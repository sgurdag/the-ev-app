package com.safagurdag.theevapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.safagurdag.theevapp.ui.navigation.graphs.profileNavigation
import com.safagurdag.theevapp.ui.navigation.routes.AppRoute
import com.safagurdag.theevapp.view.profile.screen.ProfileScreen
import com.safagurdag.theevapp.view.launch.LaunchScreen
import com.safagurdag.theevapp.view.main.AppState


@Composable
fun AppNavigationHost(
    appState: AppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = appState.startDestination.name
    )
    {

        // Launch
        composable(
            route = AppRoute.Launch.name
        ) {
            LaunchScreen(appState = appState)
        }

        // Profile
        composable(
            route = AppRoute.Launch.name
        ) {
            ProfileScreen(appState = appState)
        }

        profileNavigation(appState = appState)
    }
}