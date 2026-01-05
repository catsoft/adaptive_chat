package com.catsoft.adaptivechat.ui.kit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun SetPlatformTheme(colorScheme: ColorScheme, isDarkTheme: Boolean)