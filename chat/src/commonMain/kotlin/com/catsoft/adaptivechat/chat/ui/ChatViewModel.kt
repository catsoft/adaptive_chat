package com.catsoft.adaptivechat.chat.ui

import com.catsoft.adaptivechat.ai.service.AIService
import com.catsoft.adaptivechat.ui.kit.viewModel.ACViewModel

class ChatViewModel(
    private val geminiService: AIService,
    private val conversationId: String,
    private val agentId: String
) : ACViewModel<ChatDomainState>() {

    override fun defaultState(): ChatDomainState = ChatDomainState()
}