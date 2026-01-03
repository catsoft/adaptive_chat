package com.catsoft.adaptivechat.conversation.navigtion

import androidx.navigation.NavGraphBuilder
import com.catsoft.adaptivechat.conversation.navigation.ConversationsScreens
import com.catsoft.adaptivechat.conversation.ui.ConversationsScreen
import com.catsoft.adaptivechat.util.composableTheme

fun NavGraphBuilder.conversationNavigation() {
    composableTheme<ConversationsScreens.ConversationsList> { ConversationsScreen() }
}