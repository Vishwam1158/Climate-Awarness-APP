package com.viz.nasa_simaplequizgame

object Routes {
    const val Home = "home"
    const val CityInput = "city_input"



    // AQI Quiz Phases
    const val AQIInfo = "aqi_info/{cityName}"
    const val AQIGuess = "aqi_guess/{cityName}"
    const val AQITips = "aqi_tips/{cityName}"

    //Waste Generation
    const val WGInfo = "wg_info/{cityName}"
    const val WGGuess = "wg_guess/{cityName}"
    const val WGTips = "wg_tips/{cityName}"

    // WQI Quiz Phases (example for second question)
    const val WQIInfo = "wqi_info/{cityName}"
    const val WQIGuess = "wqi_guess/{cityName}"
    const val WQITips = "wqi_tips/{cityName}"

    //temperature variance
    const val TVInfo = "tv_info/{cityName}"
    const val TVGuess = "tv_guess/{cityName}"
    const val TVTips = "tv_tips/{cityName}"

    //RE
    const val REInfo = "re_info/{cityName}"
    const val REGuess = "re_guess/{cityName}"
    const val RETips = "re_tips/{cityName}"

    //GGE
    const val GGEInfo = "gge_info/{cityName}"
    const val GGEGuess = "gge_guess/{cityName}"
    const val GGETips = "gge_tips/{cityName}"

    //CFP
    const val CFPInfo = "cfp_info/{cityName}"
    const val CFPGuess = "cfp_guess/{cityName}"
    const val CFPTips = "cfp_tips/{cityName}"

    // Add other questions as needed
    const val Finish = "finish"
}


