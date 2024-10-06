package com.viz.nasa_simaplequizgame

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.math.abs

// Data Class for AQI information
//data class CityAQI(
//    val name: String,
//    val AQI: Int,
//    val category: String,
//    val lastUpdated: String
//)

//data class CitiesData(val cities: List<CityAQI>)

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClimateQuizApp()
        }
    }
}

//// Moc// Function to load AQI data (simulating from local JSON for simplicity)
//fun loadAQIData(): CitiesData {
//    val jsonData = """
//        {
//          "cities": [
//            {"name": "Mumbai", "AQI": 104, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//            {"name": "Delhi", "AQI": 315, "category": "Very Poor", "lastUpdated": "2024-10-02T09:20:00Z"},
//            {"name": "New York", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:21:00Z"}
//          ]
//        }
//    """
//    val gson = Gson()
//    val type: Type = object : TypeToken<CitiesData>() {}.type
//    return gson.fromJson(jsonData, type)
//}



 //Function to load AQI data (simulating from local JSON for simplicity)
//fun loadAQIData(): CitiesData {
//    val jsonData = """
//        {
//  "cities": [
//    {"name": "Mumbai", "AQI": 104, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ahmedabad", "AQI": 106, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Surat", "AQI": 98, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Vadodara", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rajkot", "AQI": 102, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Delhi", "AQI": 150, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bangalore", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hyderabad", "AQI": 89, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chennai", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kolkata", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Pune", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "AQI": 105, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "AQI": 135, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Agra", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "New York", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chicago", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Houston", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Francisco", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Miami", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Philadelphia", "AQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Washington, D.C.", "AQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Boston", "AQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Diego", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Tokyo", "AQI": 45, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "London", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Paris", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Sydney", "AQI": 40, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dubai", "AQI": 110, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"}
////    {"name": "Toronto", "AQI": 50, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Berlin", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Singapore", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Jaipur", "AQI": 115, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Lucknow", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Kanpur", "AQI": 130, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Nagpur", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Thane", "AQI": 100, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Visakhapatnam", "AQI": 93, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Ludhiana", "AQI": 112, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Nashik", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Faridabad", "AQI": 140, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Ghaziabad", "AQI": 145, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Coimbatore", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Madurai", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Dallas", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Phoenix", "AQI": 88, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Atlanta", "AQI": 82, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Seattle", "AQI": 50, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Mexico City", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Moscow", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Hong Kong", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Madrid", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
////    {"name": "Rome", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    ]
//  }
//
//    """
//    val gson = Gson()
//    val type: Type = object : TypeToken<CitiesData>() {}.type
//    return gson.fromJson(jsonData, type)
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClimateQuizApp()
}



//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            NasaSimapleQuizGameTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//
//                }
//            }
//        }
//    }
//}
