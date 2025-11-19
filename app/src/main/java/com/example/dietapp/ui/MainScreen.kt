package com.example.dietapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dietapp.ui.components.NeuroBottomBar
import com.example.dietapp.ui.components.NeuroTopBar
import com.example.dietapp.ui.components.Screen
import com.example.dietapp.ui.dashboard.DashboardScreen
import com.example.dietapp.ui.diet.DietScreen
import com.example.dietapp.ui.medical.MedicalScreen
import com.example.dietapp.ui.profile.ProfileScreen
import com.example.dietapp.ui.scanner.ScannerScreen
import com.example.dietapp.ui.wellness.WellnessScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            NeuroTopBar(
                onSettingsClick = { /* TODO */ },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        },
        bottomBar = {
            NeuroBottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.route
            ) {
                composable(Screen.Dashboard.route) { DashboardScreen() }
                composable(Screen.Diet.route) { DietScreen() }
                composable(Screen.Scanner.route) { ScannerScreen() }
                composable(Screen.Medical.route) { MedicalScreen() }
                composable(Screen.Wellness.route) { WellnessScreen() }
                composable(Screen.Profile.route) { ProfileScreen() }
            }
        }
    }
}
