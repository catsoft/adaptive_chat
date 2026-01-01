package com.catsoft.adaptivechat.agent.api.repository

import com.catsoft.adaptivechat.agent.api.model.Agent
import com.catsoft.adaptivechat.shared.api.Repository
import kotlinx.coroutines.flow.StateFlow

interface AgentRepository : Repository {
    val agents: StateFlow<List<Agent>>
    
    fun getAgent(agentId: String): Agent?
}
