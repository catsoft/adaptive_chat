package com.catsoft.adaptivechat.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.ai.service.AIService
import com.catsoft.adaptivechat.chat.api.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(
    private val geminiService: AIService,
    private val conversationId: String,
    private val agentId: String
) : ViewModel() {
    
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
}
