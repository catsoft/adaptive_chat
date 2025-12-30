package com.catsoft.adaptivechat

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.catsoft.adaptivechat.service.DataRepository
import com.catsoft.adaptivechat.service.GeminiService
import com.catsoft.adaptivechat.ui.AgentsScreen
import com.catsoft.adaptivechat.ui.ChatScreen
import com.catsoft.adaptivechat.ui.ConversationsScreen
import com.catsoft.adaptivechat.viewmodel.AgentsViewModel
import com.catsoft.adaptivechat.viewmodel.ChatViewModel
import com.catsoft.adaptivechat.viewmodel.ConversationsViewModel

sealed class Screen {
    object Conversations : Screen()
    object Agents : Screen()
    data class Chat(val conversationId: String, val agentId: String) : Screen()
}

@Composable
fun App(
    geminiApiKey: String = "YOUR_API_KEY_HERE",
    onVoiceInput: () -> Unit = {},
    onImageInput: (callback: (ByteArray) -> Unit) -> Unit = {},
    onDocumentInput: (callback: (ByteArray, String) -> Unit) -> Unit = {}
) {
    MaterialTheme {
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
