package com.edufelip.catapp.ui.navigation

import androidx.navigation.NavHostController

sealed class Routes(val route: String, val argumentKey: String) {
    fun navigate(navHostController: NavHostController) {
        navHostController.navigate(route)
    }

    fun <T> navigateWithArgument(
        navHostController: NavHostController,
        argumentValue: T?
    ) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            key = argumentKey,
            value = argumentValue
        )
        navigate(navHostController)
    }

    fun <T> navigateWithArgumentList(
        navHostController: NavHostController,
        argumentValue: List<T>?
    ) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            key = argumentKey,
            value = argumentValue
        )
        navigate(navHostController)
    }

    data object HomeRoute : Routes(
        route = HOME_ROUTE,
        argumentKey = HOME_ARGS_KEY
    )

    data object DetailsRoute : Routes(
        route = DETAILS_ROUTE,
        argumentKey = DETAILS_ARGS_KEY
    )

    companion object {
        const val HOME_ROUTE = "home_screen"
        const val HOME_ARGS_KEY = "args_home_screen"
        const val DETAILS_ROUTE = "cat_details"
        const val DETAILS_ARGS_KEY = "args_cat_details"
    }
}