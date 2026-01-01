package com.catsoft.adaptivechat.ai.service

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.shared.api.Service

interface AIService : Service {
    suspend fun sendMessage(
        message: String,
        conversationHistory: List<Message> = emptyList(),
        systemPrompt: String = ""
    ): Message
    
    suspend fun processVoiceInput(audioData: ByteArray): String
    
    suspend fun processImageInput(imageData: ByteArray): String
    
    suspend fun processDocumentInput(documentData: ByteArray, fileName: String): String
}
