package com.catsoft.adaptivechat.ui.kit.topBar

import com.catsoft.adaptivechat.ui.kit.api.delegates.ComposableContent
import com.catsoft.adaptivechat.ui.kit.api.textClause.TextClause
import com.catsoft.adaptivechat.ui.kit.topBar.states.BackIconState
import com.catsoft.adaptivechat.ui.kit.topBar.states.TitleStyle
import com.catsoft.adaptivechat.ui.kit.topBar.states.TopBarAction
import com.catsoft.adaptivechat.ui.kit.topBar.states.TopBarStyle

//knowledge about Top Bar
//https://medium.com/@muhamed.riyas/jetpack-compose-appbars-from-basics-to-advanced-scroll-behaviors-44a3a6cc62f0
data class TopBarState(
    val title: TextClause? = null,
    val titleIcon: ComposableContent = {},
    val backIcon: BackIconState = BackIconState.None,
    val actions: List<TopBarAction> = emptyList(),
    val customTitle: ComposableContent = {},
    val titleStyle: TitleStyle = TitleStyle.Normal,
    val style: TopBarStyle = TopBarStyle.Default,
    val isLoading: Boolean = false,
    val onNavigationBack: (() -> Unit)? = null,
    val shade: Boolean = false,
) {
    companion object {
        fun empty() = TopBarState().setStyle(TopBarStyle.Hidden)

        fun plainTitle(title: TextClause) = TopBarState(title = title).setBackIcon(BackIconState.Back).setStyle(TopBarStyle.Default)

        fun bigTitle(title: TextClause) = TopBarState(title = title, titleStyle = TitleStyle.Big).setBackIcon(BackIconState.Back).setStyle(TopBarStyle.Medium)

        fun rootTitle(title: TextClause) = TopBarState(title = title, titleStyle = TitleStyle.Large).setBackIcon(BackIconState.None).setShade(false).setStyle(TopBarStyle.Default)

        fun customTitle(titleUi: ComposableContent) = TopBarState(title = null, customTitle = titleUi)

        fun TopBarState.onNavigationBack(onNavigationBack: () -> Unit) = this.copy(onNavigationBack = onNavigationBack)

        fun TopBarState.setActions(vararg buttons: TopBarAction) = this.copy(actions = buttons.toList())

        fun TopBarState.setNavIcon(backIcon: BackIconState) = this.copy(backIcon = backIcon)

        fun TopBarState.setBackIcon(backIcon: BackIconState) = this.copy(backIcon = backIcon)

        fun TopBarState.setShade(shade: Boolean) = this.copy(shade = shade)

        fun TopBarState.setTitle(title: TextClause) = this.copy(title = title)

        fun TopBarState.setStyle(style: TopBarStyle) = this.copy(style = style)

        fun TopBarState.setTitleStyle(titleStyle: TitleStyle) = this.copy(titleStyle = titleStyle)

        fun TopBarState.setTitleIcon(titleIcon: ComposableContent) = this.copy(titleIcon = titleIcon)

        fun TopBarState.setLoading(isLoading: Boolean) = this.copy(isLoading = isLoading)
    }
}