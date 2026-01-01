package com.catsoft.adaptivechat.conversation.api.model

import com.catsoft.adaptivechat.chat.api.model.Message
import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val id: String,
    val title: String,
    val lastMessage: String,
    val timestamp: Long,
    val agentId: String,
    val messages: List<Message> = emptyList()
)
