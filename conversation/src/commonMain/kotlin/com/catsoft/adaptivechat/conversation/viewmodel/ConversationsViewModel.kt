package com.catsoft.adaptivechat.conversation.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.data.DataRepository
import com.catsoft.adaptivechat.conversation.domain.Conversation
import kotlinx.coroutines.flow.StateFlow

class ConversationsViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    
    val conversations: StateFlow<List<Conversation>> = dataRepository.conversations
    
    fun deleteConversation(conversationId: String) {
        // Implement delete functionality if needed
    }
}
