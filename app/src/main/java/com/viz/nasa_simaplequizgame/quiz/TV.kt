package com.viz.nasa_simaplequizgame.quiz

import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viz.nasa_simaplequizgame.CitiesTemperatureData
import com.viz.nasa_simaplequizgame.CityTemperature

@Composable
fun TVInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Temperature Variations in last 10 years in your city",
        description = listOf(
            "◉ Some cities get really hot during the day and cold at night.",
            "◉ This is called temperature change.",
            "◉ Big cities can be warmer than the countryside because of all the buildings and cars."
        ),
        cityName = cityName,
        onNext = onNext
    )
}

@Composable
fun TVGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadTemperatureData()
    val actualTemperature = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.temperatureChange

    GuessScreen(
        title = "Guess the temperature change for $cityName",
        cityName = cityName,
        actualValue = actualTemperature?.toDouble(),  // Pass the Double value
        onNext = onNext,
        placeholder = "Between 0.5 to 3.0"
    )
}

@Composable
fun TVTipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to reduce climate impact",
        tips = listOf(
            "◉ Plant more trees/gardens.",
            "◉ Use renewable energy.",
            "◉ Reduce greenhouse gas emissions.",
            "◉ Use energy-efficient appliances.",
            "◉ Participate in community clean-up events"
        ),
        cityName = cityName,
        onNext = onNext
    )
}

fun loadTemperatureData(): List<CityTemperature> {
    val jsonData = """
        {
  "cities": [
    {"name": "Mumbai", "temperatureChange": 1.2, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "temperatureChange": 2.0, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "New York", "temperatureChange": 1.5, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Tokyo", "temperatureChange": 1.8, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Ahmedabad", "temperatureChange": 2.5, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "temperatureChange": 1.7, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bengaluru", "temperatureChange": 1.3, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "temperatureChange": 2.1, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "temperatureChange": 1.4, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "temperatureChange": 1.6, "unit": "°C", "lastUpdated": "2024-10-02T09:19:00Z"}
  ]
}

    """
    val gson = Gson()
    val type = object : TypeToken<CitiesTemperatureData>() {}.type
    val citiesData: CitiesTemperatureData = gson.fromJson(jsonData, type)
    return citiesData.cities
}
