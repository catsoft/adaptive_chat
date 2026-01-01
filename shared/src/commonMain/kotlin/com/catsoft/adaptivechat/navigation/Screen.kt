package com.catsoft.adaptivechat.navigation

sealed class Screen {
    object Conversations : Screen()
    object Agents : Screen()
    data class Chat(val conversationId: String, val agentId: String) : Screen()
}
