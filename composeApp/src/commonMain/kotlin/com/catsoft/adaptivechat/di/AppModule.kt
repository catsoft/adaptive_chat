package com.catsoft.adaptivechat.di

import com.catsoft.adaptivechat.agent.di.agentModule
import com.catsoft.adaptivechat.chat.di.chatModule
import com.catsoft.adaptivechat.conversation.di.conversationModule
import com.catsoft.adaptivechat.gemini.ai.di.geminiModule
import com.catsoft.adaptivechat.localization.di.localizationModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val appModule = module {
    loadKoinModules(
        listOf(
            localizationModule,
            conversationModule,
            chatModule,
            agentModule,
            geminiModule,
        )
    )
}
