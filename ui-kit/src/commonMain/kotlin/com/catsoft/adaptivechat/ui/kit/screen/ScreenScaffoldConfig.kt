package com.chatfuel.shared.uiKit.screens.ui

import com.catsoft.adaptivechat.ui.kit.topBar.states.TopBarScrollBehavior

data class ScreenScaffoldConfig(
    val withRefreshing: Boolean = true,
    val withScroll: Boolean = true,
    val withBottomOffset: Boolean = true,
    val withBottomNavigation: Boolean = false,
    val scrollBehavior: TopBarScrollBehavior = TopBarScrollBehavior.Defaults,
) {
    companion object {
        val RootScreenConfig = ScreenScaffoldConfig(
            withRefreshing = true,
            withScroll = false,
            withBottomOffset = false,
            withBottomNavigation = true,
            scrollBehavior = TopBarScrollBehavior.None
        )

        val ListScreenConfig = ScreenScaffoldConfig(
            withRefreshing = true,
            withScroll = true,
            withBottomOffset = true,
            withBottomNavigation = false,
            scrollBehavior = TopBarScrollBehavior.Defaults
        )

        val FullScreenConfig = ScreenScaffoldConfig(
            withRefreshing = false,
            withScroll = false,
            withBottomOffset = false,
            withBottomNavigation = false,
            scrollBehavior = TopBarScrollBehavior.None
        )
    }
}