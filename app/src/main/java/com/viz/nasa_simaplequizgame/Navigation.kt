package com.viz.nasa_simaplequizgame

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ClimateQuizApp() {
    // Create a NavController
    val navController = rememberNavController()

    // Define the NavHost and routes
    NavHost(navController = navController, startDestination = Routes.Home) {

        // Home Screen
        composable(Routes.Home) {
            HomeScreen(
                onStartQuiz = { navController.navigate(Routes.CityInput) }
            )
        }

        // City Input Screen
        composable(Routes.CityInput) {
            CityInputScreen(
                onSubmitCity = { cityName ->
                    navController.navigate("quiz/$cityName")
                }
            )
        }

        // Quiz Screen
        composable(Routes.Quiz) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            QuizScreen(
                cityName = cityName,
                onQuizCompleted = { navController.navigate(Routes.CityInput) },
                onShowTips = { navController.navigate(Routes.Tips) }
            )
        }

        // Tips Screen
        composable(Routes.Tips) {
            TipsScreen(
                onBackToQuiz = { navController.navigateUp() }
            )
        }
    }
}
