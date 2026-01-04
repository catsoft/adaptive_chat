package com.catsoft.adaptivechat.ui.kit.topBar

import androidx.navigation.NavDestination

class TopBarVisibilityStateImpl: TopBarVisibilityState {
    override fun resolve(screen: NavDestination?): Boolean {
        return true
    }
}