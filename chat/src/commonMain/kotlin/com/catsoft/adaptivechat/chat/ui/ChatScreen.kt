package com.catsoft.adaptivechat.chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adaptivechat.localization.resources.chat_screen_title
import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.chat.api.navigation.ChatScreens
import com.catsoft.adaptivechat.chat.ui.input.ChatInput
import com.catsoft.adaptivechat.chat.ui.messages.MessageBubble
import com.catsoft.adaptivechat.localization.Strings
import com.catsoft.adaptivechat.ui.kit.api.textClause.str
import com.catsoft.adaptivechat.ui.kit.data.mapStates
import com.catsoft.adaptivechat.ui.kit.modifier.alCH
import com.catsoft.adaptivechat.ui.kit.modifier.arC
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.ms
import com.catsoft.adaptivechat.ui.kit.modifier.p
import com.catsoft.adaptivechat.ui.kit.modifier.s
import com.catsoft.adaptivechat.ui.kit.modifier.sv
import com.catsoft.adaptivechat.ui.kit.screen.BoxScreenScaffold
import com.catsoft.adaptivechat.ui.kit.screen.ScreenScaffoldConfig
import com.catsoft.adaptivechat.ui.kit.text.ACText
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
                DataState(
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
        modifier = ms.p(32),
        horizontalAlignment = alCH,
        verticalArrangement = arC,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.Send,
            contentDescription = null,
            modifier = 64.s,
            tint = MaterialTheme.colorScheme.primary
        )
        16.sv
        ACText(
            text = Strings.chat_empty_start_conversation.str(),
            style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
        )
        8.sv
        ACText(
            text = Strings.chat_empty_start_conversation_description.str(),
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}


@Composable

private fun DataState(
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