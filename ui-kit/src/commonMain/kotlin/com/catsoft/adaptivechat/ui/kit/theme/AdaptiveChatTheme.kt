package com.catsoft.adaptivechat.ui.kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFECECEC),              // Light gray primary
    onPrimary = Color(0xFF1A1A1A),            // Very dark text
    primaryContainer = Color(0xFF2F2F2F),     // Dark gray container
    onPrimaryContainer = Color(0xFFECECEC),   // Light text
    secondary = Color(0xFFA0A0A0),            // Medium light gray
    onSecondary = Color(0xFF1A1A1A),          // Dark text
    secondaryContainer = Color(0xFF444654),   // ChatGPT assistant message bg
    onSecondaryContainer = Color(0xFFECECEC), // Light text
    tertiary = Color(0xFF8E8E8E),             // Medium gray accent
    onTertiary = Color(0xFF1A1A1A),           // Dark text
    background = Color(0xFF212121),           // ChatGPT dark background
    onBackground = Color(0xFFECECEC),         // Light text
    surface = Color(0xFF2F2F2F),              // Dark surface
    onSurface = Color(0xFFECECEC),            // Light text
    surfaceVariant = Color(0xFF343541),       // User message background
    onSurfaceVariant = Color(0xFFD1D1D6),     // Light text
    error = Color(0xFFD14343),                // Red error
    onError = Color.White,                    // White on error
    outline = Color(0xFF4A4A4A),              // Border
    outlineVariant = Color(0xFF3A3A3A),       // Subtle border
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF2D2D2D),              // Dark gray primary
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF7F7F8),     // Very light gray container
    onPrimaryContainer = Color(0xFF2D2D2D),   // Dark text on light
    secondary = Color(0xFF6B6B6B),            // Medium gray
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF7F7F8),   // Light gray background
    onSecondaryContainer = Color(0xFF2D2D2D), // Dark text
    tertiary = Color(0xFF8B8B8B),             // Light gray accent
    onTertiary = Color.White,
    background = Color(0xFFFFFFFF),           // Pure white
    onBackground = Color(0xFF2D2D2D),         // Dark text
    surface = Color(0xFFFAFAFA),              // Very light gray surface
    onSurface = Color(0xFF2D2D2D),            // Dark text
    surfaceVariant = Color(0xFFF0F0F0),       // Light gray variant
    onSurfaceVariant = Color(0xFF6B6B6B),     // Medium gray text
    error = Color(0xFFD14343),                // Red error
    onError = Color.White,
    outline = Color(0xFFE0E0E0),              // Border gray
    outlineVariant = Color(0xFFEDEDED),       // Light border
)

private val Typography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.Normal,
    ),
    displayMedium = TextStyle(
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.Normal,
    ),
    displaySmall = TextStyle(
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
)

@Composable
fun AdaptiveChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    SetPlatformTheme(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
