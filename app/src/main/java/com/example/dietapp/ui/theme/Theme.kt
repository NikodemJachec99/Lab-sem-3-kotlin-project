package com.example.dietapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import java.util.Calendar

private val MorningColorScheme = lightColorScheme(
    primary = MorningPrimary,
    secondary = MorningSecondary,
    background = MorningBackground,
    surface = MorningSurface,
    onPrimary = MorningOnPrimary,
    onBackground = MorningOnBackground
)

private val EveningColorScheme = darkColorScheme(
    primary = EveningPrimary,
    secondary = EveningSecondary,
    background = EveningBackground,
    surface = EveningSurface,
    onPrimary = EveningOnPrimary,
    onBackground = EveningOnBackground
)

private val NightColorScheme = darkColorScheme(
    primary = NightPrimary,
    secondary = NightSecondary,
    background = NightBackground,
    surface = NightSurface,
    onPrimary = NightOnPrimary,
    onBackground = NightOnBackground
)

enum class DayPeriod {
    MORNING, EVENING, NIGHT
}

fun getDayPeriod(): DayPeriod {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (hour) {
        in 6..16 -> DayPeriod.MORNING
        in 17..21 -> DayPeriod.EVENING
        else -> DayPeriod.NIGHT
    }
}

@Composable
fun NeuroDietTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled by default to enforce our custom palettes
    content: @Composable () -> Unit
) {
    val period = getDayPeriod()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        period == DayPeriod.MORNING -> MorningColorScheme
        period == DayPeriod.EVENING -> EveningColorScheme
        else -> NightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = period == DayPeriod.MORNING
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
