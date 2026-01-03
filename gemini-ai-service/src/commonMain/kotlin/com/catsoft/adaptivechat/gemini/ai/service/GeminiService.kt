package com.catsoft.adaptivechat.gemini.ai.service

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.chat.api.model.MessageType
import com.catsoft.adaptivechat.util.IdGenerator
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig? = null
)

@Serializable
data class Content(
    val parts: List<Part>
)

@Serializable
data class Part(
    val text: String
)

@Serializable
data class GenerationConfig(
    val temperature: Double = 0.9,
    val topK: Int = 1,
    val topP: Double = 1.0,
    val maxOutputTokens: Int = 2048
)

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>
)

@Serializable
data class Candidate(
    val content: Content
)

class GeminiService(private val apiKey: String) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
    
    private val baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent"
    
    suspend fun sendMessage(
        message: String,
        conversationHistory: List<Message> = emptyList(),
        systemPrompt: String = ""
    ): Message {
        try {
            // Build conversation context
            val prompt = buildString {
                if (systemPrompt.isNotEmpty()) {
                    append(systemPrompt)
                    append("\n\n")
                }
                
                // Add conversation history for context
                conversationHistory.takeLast(10).forEach { msg ->
                    val role = if (msg.isFromUser) "User" else "Assistant"
                    append("$role: ${msg.content}\n")
                }
                
                append("User: $message")
            }
            
            val request = GeminiRequest(
                contents = listOf(
                    Content(
                        parts = listOf(Part(text = prompt))
                    )
                ),
                generationConfig = GenerationConfig()
            )
            
            val response: GeminiResponse = client.post("$baseUrl?key=$apiKey") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            
            val responseText = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: "Sorry, I couldn't generate a response."
            
            return Message(
                id = IdGenerator.generate(),
                content = responseText,
                timestamp = System.currentTimeMillis(),
                isFromUser = false,
                type = MessageType.TEXT
            )
        } catch (e: Exception) {
            // Log the detailed error for debugging (in production, use proper logging)
            println("Gemini API Error: ${e.message}")
            
            // Return user-friendly error message
            return Message(
                id = IdGenerator.generate(),
                content = "Sorry, I couldn't process your request. Please try again.",
                timestamp = System.currentTimeMillis(),
                isFromUser = false,
                type = MessageType.TEXT
            )
        }
    }
    
    suspend fun processVoiceInput(audioData: ByteArray): String {
        // Placeholder for voice processing
        // In a real implementation, this would use Gemini's multimodal capabilities
        delay(500)
        return "Voice input received (transcription would happen here)"
    }
    
    suspend fun processImageInput(imageData: ByteArray): String {
        // Placeholder for image processing
        // In a real implementation, this would use Gemini's vision capabilities
        delay(500)
        return "Image input received (analysis would happen here)"
    }
    
    suspend fun processDocumentInput(documentData: ByteArray, fileName: String): String {
        // Placeholder for document processing
        delay(500)
        return "Document '$fileName' received (processing would happen here)"
    }
}
