package com.catsoft.adaptivechat.gemini.ai.di

import com.catsoft.adaptivechat.ai.service.AIService
import com.catsoft.adaptivechat.gemini.ai.service.GeminiAIService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val geminiModule = module {
    singleOf(::GeminiAIService) bind AIService::class
}