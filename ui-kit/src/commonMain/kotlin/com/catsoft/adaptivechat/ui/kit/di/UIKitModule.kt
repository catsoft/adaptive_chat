package com.catsoft.adaptivechat.ui.kit.di

import com.catsoft.adaptivechat.ui.kit.topBar.TopBarVisibilityState
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarVisibilityStateImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val uiKitModule = module {
    singleOf(::TopBarVisibilityStateImpl) bind TopBarVisibilityState::class
}