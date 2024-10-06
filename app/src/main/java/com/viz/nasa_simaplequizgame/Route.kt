package com.viz.nasa_simaplequizgame

object Routes {
    const val Home = "home"
    const val CityInput = "city_input"

    // AQI Quiz Phases
    const val AQIInfo = "aqi_info/{cityName}"
    const val AQIGuess = "aqi_guess/{cityName}"
    const val AQITips = "aqi_tips/{cityName}"

    const val TVInfo = "tv_info/{cityName}"
    const val TVGuess = "tv_guess/{cityName}"
    const val TVTips = "tv_tips/{cityName}"

    // WQI Quiz Phases (example for second question)
    const val WQIInfo = "wqi_info/{cityName}"
    const val WQIGuess = "wqi_guess/{cityName}"
    const val WQITips = "wqi_tips/{cityName}"

    // Add other questions as needed
    const val Finish = "finish"
}


