package com.catsoft.adaptivechat.chat.api.repository

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.shared.api.Repository
import kotlinx.coroutines.flow.Flow

interface ChatRepository : Repository {
    fun getMessages(conversationId: String): Flow<List<Message>>
    suspend fun addMessage(conversationId: String, message: Message)
    suspend fun updateMessages(conversationId: String, messages: List<Message>)
}
