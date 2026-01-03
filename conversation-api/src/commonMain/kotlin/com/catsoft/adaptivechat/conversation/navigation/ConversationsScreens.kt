package com.catsoft.adaptivechat.conversation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ConversationsScreens {

    @Serializable
    object ConversationsList : ConversationsScreens()
}
