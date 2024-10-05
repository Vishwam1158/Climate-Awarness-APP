package com.viz.nasa_simaplequizgame
//
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import java.lang.reflect.Type
//
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
//    {"name": "Jaipur", "AQI": 115, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "AQI": 130, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Thane", "AQI": 100, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "AQI": 105, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Visakhapatnam", "AQI": 93, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "AQI": 135, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ludhiana", "AQI": 112, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Agra", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nashik", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Faridabad", "AQI": 140, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ghaziabad", "AQI": 145, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"}
//    {"name": "Coimbatore", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madurai", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "New York", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chicago", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Houston", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Francisco", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Miami", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dallas", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Philadelphia", "AQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Washington, D.C.", "AQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Boston", "AQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Diego", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Phoenix", "AQI": 88, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Atlanta", "AQI": 82, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Seattle", "AQI": 50, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Tokyo", "AQI": 45, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "London", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Paris", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Sydney", "AQI": 40, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dubai", "AQI": 110, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mexico City", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Moscow", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Toronto", "AQI": 50, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Berlin", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hong Kong", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Singapore", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madrid", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rome", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//
//  ]
//}
//
//    """
//    val gson = Gson()
//    val type: Type = object : TypeToken<CitiesData>() {}.type
//    return gson.fromJson(jsonData, type)
//}
//
//// AQI JSON
//val aqiData = """
//     {
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
//    {"name": "Jaipur", "AQI": 115, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "AQI": 130, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Thane", "AQI": 100, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "AQI": 105, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Visakhapatnam", "AQI": 93, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "AQI": 135, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ludhiana", "AQI": 112, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Agra", "AQI": 110, "category": "Poor", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nashik", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Faridabad", "AQI": 140, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ghaziabad", "AQI": 145, "category": "Unhealthy", "lastUpdated": "2024-10-02T09:19:00Z"}
//    {"name": "Coimbatore", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madurai", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "New York", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chicago", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Houston", "AQI": 95, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Francisco", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Miami", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dallas", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Philadelphia", "AQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Washington, D.C.", "AQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Boston", "AQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Diego", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Phoenix", "AQI": 88, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Atlanta", "AQI": 82, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Seattle", "AQI": 50, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Tokyo", "AQI": 45, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "London", "AQI": 80, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Paris", "AQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Sydney", "AQI": 40, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dubai", "AQI": 110, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mexico City", "AQI": 120, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Moscow", "AQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Toronto", "AQI": 50, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Berlin", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hong Kong", "AQI": 125, "category": "Unhealthy for Sensitive Groups", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Singapore", "AQI": 55, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madrid", "AQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rome", "AQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "AQI": 90, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//
//  ]
//}
//"""
//
//// WQI JSON
//val wqiData = """
//    {
//  "cities": [
//    {"name": "Mumbai", "WQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ahmedabad", "WQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Surat", "WQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Vadodara", "WQI": 69, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rajkot", "WQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Delhi", "WQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bangalore", "WQI": 80, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hyderabad", "WQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chennai", "WQI": 78, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kolkata", "WQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Pune", "WQI": 74, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "WQI": 62, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "WQI": 59, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "WQI": 63, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "WQI": 77, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "WQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Thane", "WQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "WQI": 66, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Visakhapatnam", "WQI": 73, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "WQI": 61, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ludhiana", "WQI": 64, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Agra", "WQI": 67, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nashik", "WQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Faridabad", "WQI": 58, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ghaziabad", "WQI": 60, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Coimbatore", "WQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madurai", "WQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "New York", "WQI": 72, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "WQI": 69, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chicago", "WQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Houston", "WQI": 74, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Francisco", "WQI": 78, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Miami", "WQI": 76, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dallas", "WQI": 77, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Philadelphia", "WQI": 71, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Washington, D.C.", "WQI": 65, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Boston", "WQI": 73, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Diego", "WQI": 70, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Phoenix", "WQI": 74, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Atlanta", "WQI": 79, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Seattle", "WQI": 80, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Tokyo", "WQI": 85, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "London", "WQI": 76, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Paris", "WQI": 74, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Sydney", "WQI": 90, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dubai", "WQI": 68, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mexico City", "WQI": 75, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Moscow", "WQI": 82, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Toronto", "WQI": 89, "category": "Good", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Berlin", "WQI": 77, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rome", "WQI": 81, "category": "Moderate", "lastUpdated": "2024-10-02T09:19:00Z"}
//  ]
//}
//
//"""
//
//// Temperature Variations JSON
//val tempData = """
//    {
//  "cities": [
//    {"name": "Mumbai", "temperatureVariation": 5, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ahmedabad", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Surat", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Vadodara", "temperatureVariation": 6, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rajkot", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Delhi", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bangalore", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Hyderabad", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chennai", "temperatureVariation": 6, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kolkata", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Pune", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Jaipur", "temperatureVariation": 14, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Lucknow", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Kanpur", "temperatureVariation": 13, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nagpur", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Indore", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Thane", "temperatureVariation": 6, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Bhopal", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Visakhapatnam", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Patna", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ludhiana", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Agra", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Nashik", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Faridabad", "temperatureVariation": 13, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Ghaziabad", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Coimbatore", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Madurai", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "New York", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Los Angeles", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Chicago", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Houston", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Francisco", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Miami", "temperatureVariation": 6, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dallas", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Philadelphia", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Washington, D.C.", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Boston", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "San Diego", "temperatureVariation": 6, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Phoenix", "temperatureVariation": 14, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Atlanta", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Seattle", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Tokyo", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "London", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Paris", "temperatureVariation": 7, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Sydney", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Dubai", "temperatureVariation": 10, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Mexico City", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Moscow", "temperatureVariation": 12, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Toronto", "temperatureVariation": 9, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Berlin", "temperatureVariation": 8, "lastUpdated": "2024-10-02T09:19:00Z"},
//    {"name": "Rome", "temperatureVariation": 11, "lastUpdated": "2024-10-02T09:19:00Z"}
//  ]
//}
//
//"""
//
//// Similar JSON for Greenhouse Gas Emissions, UHI, Drought Risk, Renewable Energy, Waste Generation.
