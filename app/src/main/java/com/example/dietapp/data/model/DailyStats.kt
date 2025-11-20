package com.example.dietapp.data.model

data class DailyStats(
    val caloriesEaten: Int,
    val caloriesBurned: Int,
    val caloriesGoal: Int,
    val proteinEaten: Int,
    val proteinGoal: Int,
    val carbsEaten: Int,
    val carbsGoal: Int,
    val fatEaten: Int,
    val fatGoal: Int,
    val waterDrank: Int, // in ml
    val waterGoal: Int, // in ml
    val stepsTaken: Int,
    val stepsGoal: Int
)
