package com.catsoft.adaptivechat.conversation.domain

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
