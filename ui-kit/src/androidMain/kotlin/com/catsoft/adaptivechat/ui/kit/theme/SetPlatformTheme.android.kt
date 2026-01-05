package com.catsoft.adaptivechat.ui.kit.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.catsoft.adaptivechat.ui.kit.modifier.colors

@Composable
actual fun SetPlatformTheme(colorScheme: ColorScheme, isDarkTheme: Boolean) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    val view = LocalView.current
    if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            !view.isInEditMode
        } else {
            false
        }
    ) {
        SideEffect {
            val activity = view.context as? ComponentActivity ?: return@SideEffect
            val window = activity.window

//            val color = if (isDarkTheme) colorScheme.onPrimary else colorScheme.primary
            val color = Color.Red
            val style = SystemBarStyle.auto(color.toArgb(), color.toArgb())
            activity.enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = colorScheme.onPrimary.toArgb()
                window.navigationBarColor = colorScheme.onPrimary.toArgb()
            }
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDarkTheme
        }
    }
}