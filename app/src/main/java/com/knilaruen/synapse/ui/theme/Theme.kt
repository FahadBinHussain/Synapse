package com.knilaruen.synapse.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = SynapsePrimaryDark,
    secondary = SynapseSecondaryDark,
    tertiary = SynapseTertiaryDark,
    background = SynapseBackgroundDark,
    surface = SynapseSurfaceDark,
    onPrimary = SynapseOnPrimaryDark,
    onBackground = SynapseOnBackgroundDark,
    onSurface = SynapseOnSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = SynapsePrimary,
    secondary = SynapseSecondary,
    tertiary = SynapseTertiary,
    background = SynapseBackground,
    surface = SynapseSurface,
    onPrimary = SynapseOnPrimary,
    onBackground = SynapseOnBackground,
    onSurface = SynapseOnSurface
)

@Composable
fun SynapseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled for custom branding
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}