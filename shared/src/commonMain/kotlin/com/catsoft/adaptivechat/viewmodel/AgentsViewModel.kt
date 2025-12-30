package com.catsoft.adaptivechat.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.model.Agent
import com.catsoft.adaptivechat.model.Conversation
import com.catsoft.adaptivechat.service.DataRepository
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class AgentsViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    
    val agents: StateFlow<List<Agent>> = dataRepository.agents
    
    fun createNewConversation(agent: Agent): String {
        val conversationId = Random.nextLong().toString()
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
