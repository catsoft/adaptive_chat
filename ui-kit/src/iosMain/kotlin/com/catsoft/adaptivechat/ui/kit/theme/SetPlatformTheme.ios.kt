package com.catsoft.adaptivechat.ui.kit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIWindow

@Composable
actual fun SetPlatformTheme(colorScheme: ColorScheme, isDarkTheme: Boolean) {
    val application = UIApplication.sharedApplication
    val windows = application.windows

    for (i in 0 until windows.size) {
        val window = windows[i] as? UIWindow
        window?.overrideUserInterfaceStyle = if (isDarkTheme) {
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
        } else {
            UIUserInterfaceStyle.UIUserInterfaceStyleLight
        }
    }
}