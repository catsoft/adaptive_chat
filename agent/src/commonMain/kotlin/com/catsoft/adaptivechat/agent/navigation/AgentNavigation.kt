package com.catsoft.adaptivechat.agent.navigation

import androidx.navigation.NavGraphBuilder
import com.catsoft.adaptivechat.agent.api.navigation.AgentScreens
import com.catsoft.adaptivechat.agent.ui.AgentsScreen
import com.catsoft.adaptivechat.util.composableTheme

fun NavGraphBuilder.agentNavigation() {
    composableTheme<AgentScreens.AgentList> { AgentsScreen() }
}