package com.example.dietapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dietapp.ui.MainScreen
import com.example.dietapp.ui.auth.LoginScreen
import com.example.dietapp.ui.auth.RegisterScreen
import com.example.dietapp.ui.theme.NeuroDietTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuroDietTheme {
                var isLoggedIn by remember { mutableStateOf(false) }
                var isRegistering by remember { mutableStateOf(false) }

                if (isLoggedIn) {
                    MainScreen()
                } else {
                    if (isRegistering) {
                        RegisterScreen(
                            onRegisterSuccess = { isLoggedIn = true },
                            onNavigateToLogin = { isRegistering = false }
                        )
                    } else {
                        LoginScreen(
                            onLoginSuccess = { isLoggedIn = true },
                            onNavigateToRegister = { isRegistering = true }
                        )
                    }
                }
            }
        }
    }
}
