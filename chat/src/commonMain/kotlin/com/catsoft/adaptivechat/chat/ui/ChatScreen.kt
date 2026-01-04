package com.catsoft.adaptivechat.chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adaptivechat.localization.resources.chat_screen_title
import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.chat.api.model.MessageType
import com.catsoft.adaptivechat.chat.api.navigation.ChatScreens
import com.catsoft.adaptivechat.chat.ui.input.ChatInput
import com.catsoft.adaptivechat.chat.ui.messages.MessageBubble
import com.catsoft.adaptivechat.localization.Strings
import com.catsoft.adaptivechat.platform.formatTimestamp
import com.catsoft.adaptivechat.ui.kit.api.textClause.raw
import com.catsoft.adaptivechat.ui.kit.api.textClause.str
import com.catsoft.adaptivechat.ui.kit.data.mapStates
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.ms
import com.catsoft.adaptivechat.ui.kit.screen.BoxScreenScaffold
import com.catsoft.adaptivechat.ui.kit.screen.ScreenScaffoldConfig
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState.Companion.setBackIcon
import com.catsoft.adaptivechat.ui.kit.topBar.states.BackIconState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ChatScreen(
    screen: ChatScreens.Chat,
    viewModel: ChatViewModel = koinViewModel() {
        parametersOf(screen.conversationId, screen.agentId)
    }
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    BoxScreenScaffold(
        viewModel = viewModel,
        config = ScreenScaffoldConfig.FullScreenConfig,
        state = { TopBarState.plainTitle(Strings.chat_screen_title.str()).setBackIcon(BackIconState.None) }
    ) { state ->
        mapStates(state.messages) { messages ->
            // Auto-scroll to bottom when new messages arrive
            LaunchedEffect(messages.size) {
                if (messages.isNotEmpty()) {
                    scope.launch {
                        listState.animateScrollToItem(messages.size - 1)
                    }
                }
            }

            if (messages.isEmpty()) {
                EmptyState()
            } else {
                Messages(
                    messages = messages,
                    listState = listState,
                )
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Start a conversation",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Send a message or use voice, image, or document input",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable

private fun Messages(
    messages: List<Message>,
    listState: androidx.compose.foundation.lazy.LazyListState,
) {
    Column(ms) {
        LazyColumn(
            state = listState,
            modifier = m.weight(1F),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message)
            }
        }

        ChatInput(isLoading = false)
    }
}