package com.catsoft.adaptivechat.platform

actual class FileHandler {
    actual suspend fun pickImage(): ByteArray? {
        // iOS implementation would use UIImagePickerController
        return null
    }
    
    actual suspend fun pickDocument(): Pair<ByteArray, String>? {
        // iOS implementation would use UIDocumentPickerViewController
        return null
    }
    
    actual suspend fun recordAudio(): ByteArray? {
        // iOS implementation would use AVAudioRecorder
        return null
    }
}
