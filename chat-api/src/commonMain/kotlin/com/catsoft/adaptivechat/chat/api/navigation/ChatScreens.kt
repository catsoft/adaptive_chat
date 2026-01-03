package com.catsoft.adaptivechat.chat.api.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ChatScreens {

    @Serializable
    data class Chat(val conversationId: String, val agentId: String): ChatScreens()
}