package com.catsoft.adaptivechat.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.catsoft.adaptivechat.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            App(
                geminiApiKey = "YOUR_GEMINI_API_KEY_HERE",
                onVoiceInput = {
                    // Handle voice input - would launch voice recorder
                },
                onImageInput = { callback ->
                    // Handle image input - would launch image picker
                },
                onDocumentInput = { callback ->
                    // Handle document input - would launch document picker
                }
            )
        }
    }
}
