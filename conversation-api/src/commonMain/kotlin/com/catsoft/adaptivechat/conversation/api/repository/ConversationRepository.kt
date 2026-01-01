package com.catsoft.adaptivechat.conversation.api.repository

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.conversation.api.model.Conversation
import com.catsoft.adaptivechat.shared.api.Repository
import kotlinx.coroutines.flow.StateFlow

interface ConversationRepository : Repository {
    val conversations: StateFlow<List<Conversation>>
    
    fun addConversation(conversation: Conversation)
    fun updateConversation(conversationId: String, messages: List<Message>)
    fun getConversation(conversationId: String): Conversation?
}
