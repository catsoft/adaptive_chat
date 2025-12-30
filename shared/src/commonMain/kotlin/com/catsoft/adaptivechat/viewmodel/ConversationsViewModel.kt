package com.catsoft.adaptivechat.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.model.Conversation
import com.catsoft.adaptivechat.service.DataRepository
import kotlinx.coroutines.flow.StateFlow

class ConversationsViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    
    val conversations: StateFlow<List<Conversation>> = dataRepository.conversations
    
    fun deleteConversation(conversationId: String) {
        // Implement delete functionality if needed
    }
}
