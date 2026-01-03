package com.catsoft.adaptivechat.agent.api.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AgentScreens {

    @Serializable
    object AgentList: AgentScreens()
}