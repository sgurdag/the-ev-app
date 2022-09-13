package com.safagurdag.theevapp.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.safagurdag.theevapp.ui.navigation.routes.AppRoute
import com.safagurdag.theevapp.view.profile.screen.ProfileScreen
import com.safagurdag.theevapp.view.main.AppState


/**
 * Define composable navigation graph to profile in nav-host.
 */
fun NavGraphBuilder.profileNavigation(
    appState: AppState
) {

    navigation(
        startDestination = "/",
        route = AppRoute.Profile.name
    ) {

        composable(
            route = "/"
        ) {
            ProfileScreen(
                appState = appState
            )
        }
    }
}