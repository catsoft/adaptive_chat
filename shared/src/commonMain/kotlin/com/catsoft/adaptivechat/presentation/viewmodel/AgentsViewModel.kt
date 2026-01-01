package com.catsoft.adaptivechat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.data.DataRepository
import com.catsoft.adaptivechat.domain.model.Agent
import com.catsoft.adaptivechat.domain.model.Conversation
import com.catsoft.adaptivechat.util.IdGenerator
import kotlinx.coroutines.flow.StateFlow

class AgentsViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    
    val agents: StateFlow<List<Agent>> = dataRepository.agents
    
    fun createNewConversation(agent: Agent): String {
        val conversationId = IdGenerator.generate()
        val conversation = Conversation(
            id = conversationId,
            title = "Chat with ${agent.name}",
            lastMessage = "",
            timestamp = System.currentTimeMillis(),
            agentId = agent.id,
            messages = emptyList()
        )
        dataRepository.addConversation(conversation)
        return conversationId
    }
}
