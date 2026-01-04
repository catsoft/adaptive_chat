package com.catsoft.adaptivechat.chat.ui.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.chat.api.model.MessageType
import com.catsoft.adaptivechat.platform.formatTimestamp
import com.catsoft.adaptivechat.ui.kit.modifier.colors
import com.catsoft.adaptivechat.ui.kit.modifier.typo


@Composable
internal fun MessageBubble(message: Message) {
    val alignment = if (message.isFromUser) Alignment.End else Alignment.Start
    val backgroundColor = if (message.isFromUser) {
        colors.primaryContainer
    } else {
        colors.secondaryContainer
    }
    val textColor = if (message.isFromUser) {
        colors.onPrimaryContainer
    } else {
        colors.onSecondaryContainer
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
                            style = typo.labelSmall,
                            color = textColor
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = message.content,
                    style = typo.bodyMedium,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatTimestamp(message.timestamp),
                    style = typo.labelSmall,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}
