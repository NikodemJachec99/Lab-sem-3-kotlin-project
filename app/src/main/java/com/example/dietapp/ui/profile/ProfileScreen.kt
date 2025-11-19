package com.example.dietapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.ui.components.GlassCard
import com.example.dietapp.ui.components.GlassTextField
import com.example.dietapp.ui.components.LiquidButton
import com.example.dietapp.ui.components.SectionHeader

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()
    
    // Anthropometry State
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    
    // Medical State
    var allergies by remember { mutableStateOf("") }
    var medications by remember { mutableStateOf("") }
    
    // Psychology State
    var stressLevel by remember { mutableFloatStateOf(5f) }
    
    // Goals State
    var targetWeight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Your Profile",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Anthropometry Section
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                SectionHeader(title = "Anthropometry")
                GlassTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    placeholder = "Weight (kg)"
                )
                Spacer(modifier = Modifier.height(8.dp))
                GlassTextField(
                    value = height,
                    onValueChange = { height = it },
                    placeholder = "Height (cm)"
                )
                Spacer(modifier = Modifier.height(8.dp))
                GlassTextField(
                    value = age,
                    onValueChange = { age = it },
                    placeholder = "Age"
                )
            }
        }

        // Medical Context Section
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                SectionHeader(title = "Medical Context")
                GlassTextField(
                    value = allergies,
                    onValueChange = { allergies = it },
                    placeholder = "Allergies (comma separated)"
                )
                Spacer(modifier = Modifier.height(8.dp))
                GlassTextField(
                    value = medications,
                    onValueChange = { medications = it },
                    placeholder = "Medications (comma separated)"
                )
            }
        }

        // Psychology Section
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                SectionHeader(title = "Psychology")
                Text(
                    text = "Stress Sensitivity: ${stressLevel.toInt()}/10",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Slider(
                    value = stressLevel,
                    onValueChange = { stressLevel = it },
                    valueRange = 1f..10f,
                    steps = 9
                )
            }
        }

        // Goals Section
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                SectionHeader(title = "Goals")
                GlassTextField(
                    value = targetWeight,
                    onValueChange = { targetWeight = it },
                    placeholder = "Target Weight (kg)"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LiquidButton(
            text = "Save Profile",
            onClick = { /* TODO: Save logic */ },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}
