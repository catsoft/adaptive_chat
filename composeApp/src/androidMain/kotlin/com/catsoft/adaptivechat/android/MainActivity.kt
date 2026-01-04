package com.catsoft.adaptivechat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.graphics.toArgb
import com.catsoft.adaptivechat.App
import com.catsoft.adaptivechat.ui.kit.modifier.colors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lightScrim = android.graphics.Color.TRANSPARENT
        val darkScrim = android.graphics.Color.TRANSPARENT
        val style = SystemBarStyle.auto(lightScrim, darkScrim)
        enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        setContent { App() }
    }
}
