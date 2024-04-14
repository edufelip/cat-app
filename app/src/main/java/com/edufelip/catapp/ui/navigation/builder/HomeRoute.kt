package com.edufelip.catapp.ui.navigation.builder

import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.edufelip.catapp.ui.navigation.Routes
import com.edufelip.catapp.ui.screens.home.HomeScreen
import com.edufelip.catapp.ui.utils.Transitions.exitTransition
import com.edufelip.catapp.ui.utils.Transitions.popEnterTransition

fun NavGraphBuilder.homeRoute(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Routes.HomeRoute.route,
        popEnterTransition = { popEnterTransition },
        exitTransition = { exitTransition },
    ) {
        HomeScreen(navHostController = navHostController, backPressedDispatcher = onBackPressedDispatcher)
    }
}