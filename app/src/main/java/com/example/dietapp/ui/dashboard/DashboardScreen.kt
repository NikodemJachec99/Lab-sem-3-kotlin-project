package com.example.dietapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dietapp.ui.components.CircularProgress
import com.example.dietapp.ui.components.GlassCard
import com.example.dietapp.ui.components.LiquidProgress
import com.example.dietapp.ui.components.MacroCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {
    val stats by viewModel.dailyStats.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        Column {
            Text(
                text = "Hello, User", // Placeholder for user name
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault()).format(Date()),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        // Main Calorie Card
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Calories Remaining",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(24.dp))
                
                val remaining = stats.caloriesGoal - stats.caloriesEaten
                val progress = (stats.caloriesEaten.toFloat() / stats.caloriesGoal.toFloat()).coerceIn(0f, 1f)
                
                Box(contentAlignment = Alignment.Center) {
                    CircularProgress(
                        percentage = progress,
                        number = remaining,
                        subTitle = "kcal left",
                        radius = 100.dp,
                        strokeWidth = 16.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(label = "Eaten", value = "${stats.caloriesEaten}", unit = "kcal")
                    StatItem(label = "Burned", value = "${stats.caloriesBurned}", unit = "kcal")
                    StatItem(label = "Goal", value = "${stats.caloriesGoal}", unit = "kcal")
                }
            }
        }

        // Macros Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MacroCard(
                name = "Protein",
                amount = stats.proteinEaten,
                goal = stats.proteinGoal,
                color = Color(0xFF4CAF50), // Green
                modifier = Modifier.weight(1f)
            )
            MacroCard(
                name = "Carbs",
                amount = stats.carbsEaten,
                goal = stats.carbsGoal,
                color = Color(0xFF2196F3), // Blue
                modifier = Modifier.weight(1f)
            )
            MacroCard(
                name = "Fat",
                amount = stats.fatEaten,
                goal = stats.fatGoal,
                color = Color(0xFFFFC107), // Amber
                modifier = Modifier.weight(1f)
            )
        }

        // Water & Steps Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Water Card
            GlassCard(modifier = Modifier.weight(1f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                     Text(
                        text = "Water",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val waterProgress = (stats.waterDrank.toFloat() / stats.waterGoal.toFloat()).coerceIn(0f, 1f)
                    LiquidProgress(
                        progress = waterProgress,
                        modifier = Modifier.size(120.dp),
                        liquidColor = Color(0xFF03A9F4)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${stats.waterDrank} / ${stats.waterGoal} ml",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Steps Card
            GlassCard(modifier = Modifier.weight(1f)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.height(180.dp) // Match approximate height of Water card
                ) {
                    Text(
                        text = "Steps",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Placeholder for Steps Icon or Progress
                    CircularProgress(
                        percentage = (stats.stepsTaken.toFloat() / stats.stepsGoal.toFloat()).coerceIn(0f, 1f),
                        number = stats.stepsTaken,
                        subTitle = "steps",
                        radius = 50.dp,
                        strokeWidth = 8.dp,
                        fontSize = 18,
                        color = Color(0xFFFF5722) // Deep Orange
                    )
                     Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Goal: ${stats.stepsGoal}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Bottom padding for navigation bar
    }
}

@Composable
fun StatItem(label: String, value: String, unit: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = unit,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
    }
}
