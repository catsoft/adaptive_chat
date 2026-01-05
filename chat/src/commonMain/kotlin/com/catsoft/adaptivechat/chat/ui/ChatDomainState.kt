package com.catsoft.adaptivechat.chat.ui

import com.catsoft.adaptivechat.chat.api.model.Message
import com.catsoft.adaptivechat.ui.kit.api.data.ACData

class ChatDomainState(
    val conversationId: String = "",
    val agentId: String = "",
    val messages: ACData<List<Message>> = ACData.Companion.loading(),
)