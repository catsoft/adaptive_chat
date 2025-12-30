package com.catsoft.adaptivechat

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App(
            geminiApiKey = "YOUR_GEMINI_API_KEY_HERE",
            onVoiceInput = {
                // Handle voice input - would use iOS AVAudioRecorder
            },
            onImageInput = { callback ->
                // Handle image input - would use iOS UIImagePickerController
            },
            onDocumentInput = { callback ->
                // Handle document input - would use iOS UIDocumentPickerViewController
            }
        )
    }
}
