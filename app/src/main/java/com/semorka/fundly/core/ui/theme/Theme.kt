package com.semorka.fundly.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = PrimaryTextLight,
    onSurface = PrimaryTextLight,
    surfaceVariant = SecondaryTextLight,
    primary = AccentGreenLight,
    primaryContainer = AccentGreenBackgroundLight,
    onPrimaryContainer = AccentGreenLight,
    outlineVariant = TrackColorLight
)

private val DarkColorScheme = darkColorScheme(
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = PrimaryTextDark,
    onSurface = PrimaryTextDark,
    surfaceVariant = SecondaryTextDark,
    primary = AccentGreenDark,
    primaryContainer = AccentGreenBackgroundDark,
    onPrimaryContainer = AccentGreenDark,
    outlineVariant = TrackColorDark
)

@Composable
fun FundlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}