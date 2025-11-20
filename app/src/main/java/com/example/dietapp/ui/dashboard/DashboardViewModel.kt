package com.example.dietapp.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.dietapp.data.model.DailyStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {

    private val _dailyStats = MutableStateFlow(
        DailyStats(
            caloriesEaten = 1450,
            caloriesBurned = 320,
            caloriesGoal = 2200,
            proteinEaten = 95,
            proteinGoal = 160,
            carbsEaten = 180,
            carbsGoal = 250,
            fatEaten = 45,
            fatGoal = 70,
            waterDrank = 1250,
            waterGoal = 2500,
            stepsTaken = 6540,
            stepsGoal = 10000
        )
    )
    val dailyStats: StateFlow<DailyStats> = _dailyStats.asStateFlow()

    // Function to update stats (placeholder for future logic)
    fun updateStats(newStats: DailyStats) {
        _dailyStats.value = newStats
    }
}
