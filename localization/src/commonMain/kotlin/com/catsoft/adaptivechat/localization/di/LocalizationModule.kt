package com.catsoft.adaptivechat.localization.di

import com.catsoft.adaptivechat.localization.StringsManager
import com.catsoft.adaptivechat.localization.StringsManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localizationModule = module {
    singleOf(::StringsManagerImpl) bind StringsManager::class
}
