package com.catsoft.adaptivechat.ui.kit.topBar

import androidx.navigation.NavDestination

interface TopBarVisibilityState {
    fun resolve(screen: NavDestination?) : Boolean
}