package com.catsoft.adaptivechat.ui.kit.topBar.states

import kotlinx.serialization.Serializable

@Serializable
enum class TopBarScrollBehavior {
    None,
    Defaults,
    EnterAlwaysScrollBehavior,
    ExitUntilCollapsedScrollBehavior,
    PinnedScrollBehavior,
}