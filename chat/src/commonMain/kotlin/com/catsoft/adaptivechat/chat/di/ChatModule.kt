package com.catsoft.adaptivechat.chat.di

import com.catsoft.adaptivechat.chat.ui.ChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatModule = module {
    viewModelOf(::ChatViewModel)
}