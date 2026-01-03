package com.catsoft.adaptivechat.agent.di

import com.catsoft.adaptivechat.agent.viewmodel.AgentsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val agentModule = module {
    viewModelOf(::AgentsViewModel)
}