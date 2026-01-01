package com.catsoft.adaptivechat.agent.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val systemPrompt: String
)
