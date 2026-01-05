package com.catsoft.adaptivechat

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.catsoft.adaptivechat.agent.navigation.agentNavigation
import com.catsoft.adaptivechat.chat.api.navigation.ChatScreens
import com.catsoft.adaptivechat.chat.navigation.chatNavigation
import com.catsoft.adaptivechat.conversation.navigation.ConversationsScreens
import com.catsoft.adaptivechat.conversation.navigtion.conversationNavigation
import com.catsoft.adaptivechat.ui.kit.modifier.clearFocusOnTap
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.navigation.LocalNavController
import com.catsoft.adaptivechat.ui.kit.theme.AdaptiveChatTheme

@Composable
fun App() {
    AdaptiveChatTheme {
        Box(m.clearFocusOnTap()) {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavController provides navController) {

                NavHost(
                    navController = navController,
                    startDestination = ChatScreens.Chat(conversationId = "", agentId = ""),
                    enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
                    exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() },
                    popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) + fadeIn() },
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() },
                ) {
                    conversationNavigation()

                    agentNavigation()

                    chatNavigation()
                }
            }
        }
    }
}
