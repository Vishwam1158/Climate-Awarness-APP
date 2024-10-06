package com.viz.nasa_simaplequizgame

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viz.nasa_simaplequizgame.quiz.AQIGuessScreen
import com.viz.nasa_simaplequizgame.quiz.AQIInfoScreen
import com.viz.nasa_simaplequizgame.quiz.AQITipsScreen
import com.viz.nasa_simaplequizgame.quiz.CFPGuessScreen
import com.viz.nasa_simaplequizgame.quiz.CFPInfoScreen
import com.viz.nasa_simaplequizgame.quiz.CFPTipsScreen
import com.viz.nasa_simaplequizgame.quiz.GGEGuessScreen
import com.viz.nasa_simaplequizgame.quiz.GGEInfoScreen
import com.viz.nasa_simaplequizgame.quiz.GGETipsScreen
import com.viz.nasa_simaplequizgame.quiz.REGuessScreen
import com.viz.nasa_simaplequizgame.quiz.REInfoScreen
import com.viz.nasa_simaplequizgame.quiz.RETipsScreen
import com.viz.nasa_simaplequizgame.quiz.TVGuessScreen
import com.viz.nasa_simaplequizgame.quiz.TVInfoScreen
import com.viz.nasa_simaplequizgame.quiz.TVTipsScreen
import com.viz.nasa_simaplequizgame.quiz.WQIGuessScreen
import com.viz.nasa_simaplequizgame.quiz.WQIInfoScreen
import com.viz.nasa_simaplequizgame.quiz.WQITipsScreen
import com.viz.nasa_simaplequizgame.quiz.WasteGenerationGuessScreen
import com.viz.nasa_simaplequizgame.quiz.WasteGenerationInfoScreen
import com.viz.nasa_simaplequizgame.quiz.WasteGenerationTipsScreen

@RequiresApi(Build.VERSION_CODES.P)
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

        // AQI
        composable(Routes.AQIInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQIInfoScreen(cityName = cityName, onNext = {
                navController.navigate("aqi_guess/$cityName")
            })
        }
        composable(Routes.AQIGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQIGuessScreen(cityName = cityName, onNext = {
                navController.navigate("aqi_tips/$cityName")
            })
        }
        composable(Routes.AQITips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            AQITipsScreen(cityName = cityName, onNext = {
                navController.navigate("wg_info/$cityName") // Move to next question (WQI)
            })
        }

        // WG
        composable(Routes.WGInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WasteGenerationInfoScreen(cityName = cityName, onNext = {
                navController.navigate("wg_guess/$cityName")
            })
        }
        composable(Routes.WGGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WasteGenerationGuessScreen(cityName = cityName, onNext = {
                navController.navigate("wg_tips/$cityName")
            })
        }
        composable(Routes.WGTips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WasteGenerationTipsScreen(cityName = cityName, onNext = {
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
                navController.navigate("re_info/$cityName") // Move to next question (WQI)
            })
        }

        //RE
        composable(Routes.REInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            REInfoScreen(cityName = cityName, onNext = {
                navController.navigate("re_guess/$cityName")
            })
        }
        composable(Routes.REGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            REGuessScreen(cityName = cityName, onNext = {
                navController.navigate("re_tips/$cityName")
            })
        }
        composable(Routes.RETips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            RETipsScreen(cityName = cityName, onNext = {
                navController.navigate("gge_info/$cityName") // Move to next question (WQI)
            })
        }

        //GGE
        composable(Routes.GGEInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            GGEInfoScreen(cityName = cityName, onNext = {
                navController.navigate("gge_guess/$cityName")
            })
        }
        composable(Routes.GGEGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            GGEGuessScreen(cityName = cityName, onNext = {
                navController.navigate("gge_tips/$cityName")
            })
        }
        composable(Routes.GGETips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            GGETipsScreen(cityName = cityName, onNext = {
                navController.navigate("cfp_info/$cityName") // Move to next question (WQI)
            })
        }

        //CFP
        composable(Routes.CFPInfo) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            CFPInfoScreen(cityName = cityName, onNext = {
                navController.navigate("cfp_guess/$cityName")
            })
        }
        composable(Routes.CFPGuess) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            CFPGuessScreen(cityName = cityName, onNext = {
                navController.navigate("cfp_tips/$cityName")
            })
        }
        composable(Routes.CFPTips) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            CFPTipsScreen(cityName = cityName, onNext = {
                navController.navigate(Routes.Home) // Move to next question (WQI)
            })
        }

        // Finish Screen
        composable(Routes.Finish) {
            FinishScreen(onBackToHome = { navController.navigate(Routes.Home) })
        }
    }
}
