package com.catsoft.adaptivechat.conversation.di

import com.catsoft.adaptivechat.conversation.viewmodel.ConversationsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val conversationModule = module {
    viewModelOf(::ConversationsViewModel)
}