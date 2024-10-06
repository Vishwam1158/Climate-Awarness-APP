package com.viz.nasa_simaplequizgame

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viz.nasa_simaplequizgame.quiz.AQIGuessScreen
import com.viz.nasa_simaplequizgame.quiz.AQIInfoScreen
import com.viz.nasa_simaplequizgame.quiz.AQITipsScreen
import com.viz.nasa_simaplequizgame.quiz.TVGuessScreen
import com.viz.nasa_simaplequizgame.quiz.TVInfoScreen
import com.viz.nasa_simaplequizgame.quiz.TVTipsScreen
import com.viz.nasa_simaplequizgame.quiz.WQIGuessScreen
import com.viz.nasa_simaplequizgame.quiz.WQIInfoScreen
import com.viz.nasa_simaplequizgame.quiz.WQITipsScreen

@Composable
fun ClimateQuizApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home) {

        // Home Screen
        composable(Routes.Home) {
            HomeScreen(onStartQuiz = { navController.navigate(Routes.CityInput) })
        }

        // City Input Screen
        composable(Routes.CityInput) {
            CityInputScreen(onSubmitCity = { cityName ->
                navController.navigate("aqi_info/$cityName")
            })
        }

        // AQI Info Screen
        composable(Routes.AQIInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQIInfoScreen(cityName = cityName, onNext = {
                navController.navigate("aqi_guess/$cityName")
            })
        }

        // AQI Guess Screen
        composable(Routes.AQIGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQIGuessScreen(cityName = cityName, onNext = {
                navController.navigate("aqi_tips/$cityName")
            })
        }

        // AQI Tips Screen
        composable(Routes.AQITips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQITipsScreen(cityName = cityName, onNext = {
                navController.navigate("tv_info/$cityName") // Move to next question (WQI)
            })
        }

        // TV
        composable(Routes.TVInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            TVInfoScreen(cityName = cityName, onNext = {
                navController.navigate("tv_guess/$cityName")
            })
        }
        composable(Routes.TVGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            TVGuessScreen(cityName = cityName, onNext = {
                navController.navigate("tv_tips/$cityName")
            })
        }
        composable(Routes.TVTips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            TVTipsScreen(cityName = cityName, onNext = {
                navController.navigate("wqi_info/$cityName") // Move to next question (WQI)
            })
        }


        // WQI
        composable(Routes.WQIInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WQIInfoScreen(cityName = cityName, onNext = {
                navController.navigate("wqi_guess/$cityName")
            })
        }
        composable(Routes.WQIGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WQIGuessScreen(cityName = cityName, onNext = {
                navController.navigate("wqi_tips/$cityName")
            })
        }
        composable(Routes.WQITips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WQITipsScreen(cityName = cityName, onNext = {
                navController.navigate(Routes.Home) // Move to next question (WQI)
            })
        }


        // Finish Screen
        composable(Routes.Finish) {
            FinishScreen(onBackToHome = { navController.navigate(Routes.Home) })
        }
    }
}
