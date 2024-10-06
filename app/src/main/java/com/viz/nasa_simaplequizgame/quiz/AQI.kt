package com.viz.nasa_simaplequizgame.quiz


import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viz.nasa_simaplequizgame.CitiesData
import com.viz.nasa_simaplequizgame.CityAQI


@Composable
fun AQIInfoScreen(cityName: String, onNext: () -> Unit) {
    InfoScreen(
        title = "Air Quality Index (AQI)",
        description = listOf(
            "◉ It tells us how clean or dirty the air is.\n" +
            "◉ Good air is safe to breathe, while bad air can make you feel sick.\n" +
            "◉ Cities with lots of cars and factories usually have dirtier air."
        ),
        cityName = cityName,
        onNext = onNext
    )
}

@Composable
fun AQIGuessScreen(cityName: String, onNext: () -> Unit) {
    val citiesData = loadAQIData() // Load the data for AQI
    val actualAQI = citiesData.find { it.name.equals(cityName, ignoreCase = true) }?.AQI

    if (actualAQI != null) {
        GuessScreen(
            title = "Guess the AQI for $cityName",
            cityName = cityName,
            actualValue = actualAQI.toDouble(),
            onNext = onNext,
            placeholder = "Between 0 to 500"

        )
    }
}

@Composable
fun AQITipsScreen(cityName: String, onNext: () -> Unit) {
    TipsScreen(
        title = "How to Improve AQI",
        tips = listOf(
            "◉ Plant more trees.",
            "◉ Reduce vehicle emissions.",
            "◉ Use public transportation or walk.",
            "◉ Avoid burning waste",
            "◉ Advocate for clean air policies"
        ),
        cityName = cityName,
        onNext = onNext
    )
}

fun loadAQIData(): List<CityAQI> {
    val jsonData = """
        {
  "cities": [
    {"name": "Mumbai", "AQI": 104, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Ahmedabad", "AQI": 106, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Surat", "AQI": 98, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Vadodara", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Rajkot", "AQI": 102, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Delhi", "AQI": 150, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bangalore", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Hyderabad", "AQI": 89, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chennai", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Kolkata", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Pune", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Indore", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Bhopal", "AQI": 105, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Patna", "AQI": 135, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Agra", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "New York", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Los Angeles", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Chicago", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Houston", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "San Francisco", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Miami", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Philadelphia", "AQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Washington, D.C.", "AQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Boston", "AQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "San Diego", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Tokyo", "AQI": 45, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "London", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Paris", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Sydney", "AQI": 40, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Dubai", "AQI": 110, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
    {"name": "Los Angeles", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"}
//    {"name": "Toronto", "AQI": 50, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Berlin", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Singapore", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "AQI": 115, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "AQI": 130, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Thane", "AQI": 100, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Visakhapatnam", "AQI": 93, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ludhiana", "AQI": 112, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nashik", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Faridabad", "AQI": 140, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ghaziabad", "AQI": 145, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Coimbatore", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madurai", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dallas", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Phoenix", "AQI": 88, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Atlanta", "AQI": 82, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Seattle", "AQI": 50, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mexico City", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Moscow", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hong Kong", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madrid", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rome", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
    ]
  }
    """
    val gson = Gson()
    val type = object : TypeToken<CitiesData>() {}.type

    val citiesData: CitiesData = gson.fromJson(jsonData, type)
    return citiesData.cities
}
