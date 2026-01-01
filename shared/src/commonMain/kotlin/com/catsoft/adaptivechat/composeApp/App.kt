package com.catsoft.adaptivechat.composeApp

import androidx.compose.runtime.*
import com.catsoft.adaptivechat.data.DataRepository
import com.catsoft.adaptivechat.data.GeminiService
import com.catsoft.adaptivechat.navigation.Screen
import com.catsoft.adaptivechat.presentation.ui.AgentsScreen
import com.catsoft.adaptivechat.presentation.ui.ChatScreen
import com.catsoft.adaptivechat.presentation.ui.ConversationsScreen
import com.catsoft.adaptivechat.presentation.ui.kit.theme.AdaptiveChatTheme
import com.catsoft.adaptivechat.presentation.viewmodel.AgentsViewModel
import com.catsoft.adaptivechat.presentation.viewmodel.ChatViewModel
import com.catsoft.adaptivechat.presentation.viewmodel.ConversationsViewModel

@Composable
fun App(
    geminiApiKey: String = "PLEASE_ADD_YOUR_GEMINI_API_KEY_HERE",
    onVoiceInput: () -> Unit = {},
    onImageInput: (callback: (ByteArray) -> Unit) -> Unit = {},
    onDocumentInput: (callback: (ByteArray, String) -> Unit) -> Unit = {}
) {
    AdaptiveChatTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Conversations) }
        
        // Initialize services and repository
        val geminiService = remember { GeminiService(geminiApiKey) }
        val dataRepository = remember { DataRepository() }
        
        when (val screen = currentScreen) {
            is Screen.Conversations -> {
                val conversationsViewModel = remember { ConversationsViewModel(dataRepository) }
                ConversationsScreen(
                    viewModel = conversationsViewModel,
                    onConversationClick = { conversationId ->
                        val conversation = dataRepository.getConversation(conversationId)
                        if (conversation != null) {
                            currentScreen = Screen.Chat(conversationId, conversation.agentId)
                        }
                    },
                    onNavigateToAgents = {
                        currentScreen = Screen.Agents
                    }
                )
            }
            
            is Screen.Agents -> {
                val agentsViewModel = remember { AgentsViewModel(dataRepository) }
                AgentsScreen(
                    viewModel = agentsViewModel,
                    onAgentSelect = { conversationId ->
                        val conversation = dataRepository.getConversation(conversationId)
                        if (conversation != null) {
                            currentScreen = Screen.Chat(conversationId, conversation.agentId)
                        }
                    },
                    onBack = {
                        currentScreen = Screen.Conversations
                    }
                )
            }
            
            is Screen.Chat -> {
                val chatViewModel = remember(screen.conversationId) {
                    ChatViewModel(
                        geminiService = geminiService,
                        dataRepository = dataRepository,
                        conversationId = screen.conversationId,
                        agentId = screen.agentId
                    )
                }
                
                ChatScreen(
                    viewModel = chatViewModel,
                    onBack = {
                        currentScreen = Screen.Conversations
                    },
                    onVoiceInput = onVoiceInput,
                    onImageInput = {
                        onImageInput { imageData ->
                            chatViewModel.sendImageMessage(imageData)
                        }
                    },
                    onDocumentInput = {
                        onDocumentInput { documentData, fileName ->
                            chatViewModel.sendDocumentMessage(documentData, fileName)
                        }
                    }
                )
            }
        }
    }
}
