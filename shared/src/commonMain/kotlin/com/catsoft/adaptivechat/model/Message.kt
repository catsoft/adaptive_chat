package com.catsoft.adaptivechat.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    val content: String,
    val timestamp: Long,
    val isFromUser: Boolean,
    val type: MessageType = MessageType.TEXT,
    val mediaUrl: String? = null
)

@Serializable
enum class MessageType {
    TEXT,
    VOICE,
    IMAGE,
    DOCUMENT
}
