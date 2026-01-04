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
import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.chat.api.model.MessageType
import com.catsoft.adaptivechat.chat.api.navigation.ChatScreens
import com.catsoft.adaptivechat.platform.formatTimestamp
import com.catsoft.adaptivechat.ui.kit.api.textClause.raw
import com.catsoft.adaptivechat.ui.kit.data.mapStates
import com.catsoft.adaptivechat.ui.kit.screen.BoxScreenScaffold
import com.catsoft.adaptivechat.ui.kit.screen.ScreenScaffoldConfig
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState
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
        viewModel,
        config = ScreenScaffoldConfig.FullScreenConfig,
        state = {
            TopBarState.plainTitle("AI Chat".raw())
        }
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
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(messages) { message ->
            MessageBubble(message)
        }
    }
}


@Composable
private fun ChatInput(isLoading: Boolean) {
    var messageText by remember { mutableStateOf("") }

    Surface(
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Input options row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Send, "Voice input")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Send, "Image input")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Add, "Document input")
                }
            }

            // Text input row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") },
                    maxLines = 3
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            messageText = ""
                        }
                    },
                    enabled = messageText.isNotBlank() && !isLoading
                ) {
                    Icon(Icons.Default.Send, "Send")
                }
            }
        }
    }
}


@Composable
fun MessageBubble(message: Message) {
    val alignment = if (message.isFromUser) Alignment.End else Alignment.Start
    val backgroundColor = if (message.isFromUser) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    val textColor = if (message.isFromUser) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Surface(
            modifier = Modifier.widthIn(max = 300.dp),
            shape = RoundedCornerShape(12.dp),
            color = backgroundColor
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                if (message.type != MessageType.TEXT) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (message.type) {
                                MessageType.VOICE -> Icons.Default.Send
                                MessageType.IMAGE -> Icons.Default.Send
                                MessageType.DOCUMENT -> Icons.Default.Send
                                else -> Icons.Default.Send
                            },
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = textColor
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = message.type.name,
                            style = MaterialTheme.typography.labelSmall,
                            color = textColor
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatTimestamp(message.timestamp),
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}
