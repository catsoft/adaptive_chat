package com.catsoft.adaptivechat.chat.api.navigation

sealed class ChatScreens {
    data class Chat(val conversationId: String, val agentId: String): ChatScreens()
}