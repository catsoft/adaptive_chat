package com.catsoft.adaptivechat.agent.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.agent.domain.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AgentsViewModel(
) : ViewModel() {
    
    val agents: StateFlow<List<Agent>> = MutableStateFlow(emptyList())
}
