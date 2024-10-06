package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.Composable
import com.viz.nasa_simaplequizgame.CitiesWQIData
import com.viz.nasa_simaplequizgame.CityWQI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun WQIInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Water Quality Index (WQI)",
        description = listOf(
            "◉ This shows if the water in a city is clean and safe to drink.",
                    "◉ Clean water is good for our health.",
                    "◉ Some cities have dirtier water if there’s pollution."
        ),
        cityName = cityName,
        onNext = onNext
    )
}

@Composable
fun WQIGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadWQIData() // Load the data for WQI
    val actualWQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.wqi

    if (actualWQI != null) {
        GuessScreen(
            title = "Guess the WQI for $cityName",
            cityName = cityName,
            actualValue = actualWQI.toDouble(),
            onNext = onNext,
            placeholder = "Between 0 to 100"
        )
    }
}


@Composable
fun WQITipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Improve Water Quality",
        tips = listOf(
            "◉ Use water wisely",
            "◉ Prevent water pollution.",
            "◉ Reduce water waste.",
            "◉ Avoid using harmful chemicals",
            "◉ Support water conservation programs."
        ),
        cityName = cityName,
        onNext = onNext
    )
}

fun loadWQIData(): List<CityWQI> {
    val jsonData = """
        {
  "cities": [
    {"name": "Mumbai", "WQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "WQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "New York", "WQI": 85, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Tokyo", "WQI": 80, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Ahmedabad", "WQI": 62, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "WQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bengaluru", "WQI": 75, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "WQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "WQI": 74, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "WQI": 72, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesWQIData>() {}.type
    val citiesData: CitiesWQIData = gson.fromJson(jsonData, type)
    return citiesData.cities
}


