package com.edufelip.catapp.ui

import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.edufelip.catapp.ui.navigation.Routes
import com.edufelip.catapp.ui.theme.CatTheme
import com.edufelip.catapp.ui.navigation.builder.homeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : androidx.activity.ComponentActivity() {

    @OptIn(androidx.compose.animation.ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.HomeRoute.route,
                    builder = {
                        homeRoute(
                            navHostController = navController,
                            onBackPressedDispatcher = onBackPressedDispatcher
                        )
                    }
                )
            }
        }
    }
}