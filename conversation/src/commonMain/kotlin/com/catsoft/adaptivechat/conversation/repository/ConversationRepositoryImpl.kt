package com.catsoft.adaptivechat.conversation.repository

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.conversation.api.model.Conversation
import com.catsoft.adaptivechat.conversation.api.repository.ConversationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConversationRepositoryImpl : ConversationRepository {
    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    override val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()
    
    override fun addConversation(conversation: Conversation) {
        _conversations.value = _conversations.value + conversation
    }
    
    override fun updateConversation(conversationId: String, messages: List<Message>) {
        _conversations.value = _conversations.value.map { conv ->
            if (conv.id == conversationId) {
                conv.copy(
                    messages = messages,
                    lastMessage = messages.lastOrNull()?.content ?: "",
                    timestamp = messages.lastOrNull()?.timestamp ?: conv.timestamp
                )
            } else {
                conv
            }
        }
    }
    
    override fun getConversation(conversationId: String): Conversation? {
        return _conversations.value.find { it.id == conversationId }
    }
}
