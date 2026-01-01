package com.catsoft.adaptivechat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catsoft.adaptivechat.data.DataRepository
import com.catsoft.adaptivechat.data.GeminiService
import com.catsoft.adaptivechat.domain.model.Conversation
import com.catsoft.adaptivechat.domain.model.Message
import com.catsoft.adaptivechat.domain.model.MessageType
import com.catsoft.adaptivechat.util.IdGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val geminiService: GeminiService,
    private val dataRepository: DataRepository,
    private val conversationId: String,
    private val agentId: String
) : ViewModel() {
    
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadMessages()
    }
    
    private fun loadMessages() {
        val conversation = dataRepository.getConversation(conversationId)
        _messages.value = conversation?.messages ?: emptyList()
    }
    
    fun sendTextMessage(text: String) {
        if (text.isBlank()) return
        
        val userMessage = Message(
            id = IdGenerator.generate(),
            content = text,
            timestamp = System.currentTimeMillis(),
            isFromUser = true,
            type = MessageType.TEXT
        )
        
        _messages.value = _messages.value + userMessage
        updateConversation()
        
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val agent = dataRepository.getAgent(agentId)
                val systemPrompt = agent?.systemPrompt ?: ""
                
                val response = geminiService.sendMessage(
                    message = text,
                    conversationHistory = _messages.value,
                    systemPrompt = systemPrompt
                )
                
                _messages.value = _messages.value + response
                updateConversation()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun sendVoiceMessage(audioData: ByteArray) {
        val userMessage = Message(
            id = IdGenerator.generate(),
            content = "Voice message",
            timestamp = System.currentTimeMillis(),
            isFromUser = true,
            type = MessageType.VOICE
        )
        
        _messages.value = _messages.value + userMessage
        updateConversation()
        
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val transcription = geminiService.processVoiceInput(audioData)
                sendTextMessage(transcription)
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun sendImageMessage(imageData: ByteArray) {
        val userMessage = Message(
            id = IdGenerator.generate(),
            content = "Image uploaded",
            timestamp = System.currentTimeMillis(),
            isFromUser = true,
            type = MessageType.IMAGE
        )
        
        _messages.value = _messages.value + userMessage
        updateConversation()
        
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val analysis = geminiService.processImageInput(imageData)
                sendTextMessage("Analyze this image: $analysis")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun sendDocumentMessage(documentData: ByteArray, fileName: String) {
        val userMessage = Message(
            id = IdGenerator.generate(),
            content = "Document: $fileName",
            timestamp = System.currentTimeMillis(),
            isFromUser = true,
            type = MessageType.DOCUMENT
        )
        
        _messages.value = _messages.value + userMessage
        updateConversation()
        
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val summary = geminiService.processDocumentInput(documentData, fileName)
                sendTextMessage("Summarize this document: $summary")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun updateConversation() {
        dataRepository.updateConversation(conversationId, _messages.value)
    }
}
