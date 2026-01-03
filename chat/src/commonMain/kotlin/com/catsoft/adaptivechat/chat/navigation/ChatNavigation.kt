package com.catsoft.adaptivechat.chat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.catsoft.adaptivechat.chat.api.navigation.ChatScreens
import com.catsoft.adaptivechat.chat.ui.ChatScreen
import com.catsoft.adaptivechat.util.composableTheme

fun NavGraphBuilder.chatNavigation() {
    composableTheme<ChatScreens.Chat> {
        val route = it.toRoute<ChatScreens.Chat>()
        ChatScreen(route)
    }
}