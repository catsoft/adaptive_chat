package com.catsoft.adaptivechat.data

import com.catsoft.adaptivechat.agent.domain.Agent
import com.catsoft.adaptivechat.conversation.domain.Conversation
import com.catsoft.adaptivechat.conversation.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataRepository {
    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()
    
    private val _agents = MutableStateFlow<List<Agent>>(getDefaultAgents())
    val agents: StateFlow<List<Agent>> = _agents.asStateFlow()
    
    fun addConversation(conversation: Conversation) {
        _conversations.value = _conversations.value + conversation
    }
    
    fun updateConversation(conversationId: String, messages: List<Message>) {
        _conversations.value = _conversations.value.map { conv ->
            if (conv.id == conversationId) {
                conv.copy(
                    messages = messages,
                    lastMessage = messages.lastOrNull()?.content ?: "",
                    timestamp = messages.lastOrNull()?.timestamp ?: conv.timestamp
                )
            } else {
                conv
            }
        }
    }
    
    fun getConversation(conversationId: String): Conversation? {
        return _conversations.value.find { it.id == conversationId }
    }
    
    fun getAgent(agentId: String): Agent? {
        return _agents.value.find { it.id == agentId }
    }
    
    private fun getDefaultAgents(): List<Agent> {
        return listOf(
            Agent(
                id = "general",
                name = "General Assistant",
                description = "A helpful general-purpose AI assistant",
                icon = "🤖",
                systemPrompt = "You are a helpful, friendly AI assistant. Provide clear, accurate, and concise responses."
            ),
            Agent(
                id = "code",
                name = "Code Assistant",
                description = "Expert in programming and software development",
                icon = "💻",
                systemPrompt = "You are an expert software engineer. Help with coding questions, debugging, and best practices. Provide code examples when appropriate."
            ),
            Agent(
                id = "creative",
                name = "Creative Writer",
                description = "Creative writing and storytelling assistant",
                icon = "✍️",
                systemPrompt = "You are a creative writing assistant. Help with story ideas, creative content, and engaging narratives."
            ),
            Agent(
                id = "teacher",
                name = "Teacher",
                description = "Educational assistant for learning",
                icon = "📚",
                systemPrompt = "You are a patient teacher. Explain concepts clearly, use examples, and break down complex topics into understandable parts."
            ),
            Agent(
                id = "analyst",
                name = "Data Analyst",
                description = "Data analysis and insights specialist",
                icon = "📊",
                systemPrompt = "You are a data analyst. Help analyze data, provide insights, and explain statistical concepts clearly."
            )
        )
    }
}
