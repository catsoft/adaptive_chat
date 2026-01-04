package com.catsoft.adaptivechat.ui.kit.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.catsoft.adaptivechat.logger.logger
import com.catsoft.adaptivechat.ui.kit.api.textClause.TextClause
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.mw
import com.catsoft.adaptivechat.ui.kit.topBar.states.BackIconState
import com.catsoft.adaptivechat.ui.kit.topBar.states.BackIconState.Back
import com.catsoft.adaptivechat.ui.kit.topBar.states.BackIconState.Close
import com.catsoft.adaptivechat.ui.kit.topBar.states.TitleStyle
import com.catsoft.adaptivechat.ui.kit.topBar.states.TopBarStyle
import com.chatfuel.shared.uiKit.shimmer.ShimmerNavigatorBarItem
import org.koin.compose.koinInject

@Composable
fun ChatfuelScreenTopBar(
    state: TopBarState?,
    modifier: Modifier = m,
    scrollBehavior: TopAppBarScrollBehavior?,
    canNavigateBack: Boolean = true,
    navigateUp: (() -> Unit)? = null,
) {
    val navController = LocalNavController.current
    val backStackEntry by navController!!.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination ?: return
    val navigateUp: () -> Unit = navigateUp ?: state?.onNavigationBack ?: { navController.navigateUp() }

    val columnModifier = if (state?.shade == true) {
        mw.animateContentSize().padding(bottom = 32.dp)
    } else {
        mw.animateContentSize()
    }.shadow(
        elevation = 16.dp,
        spotColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        ambientColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
        shape = RectangleShape,
    ).background(MaterialTheme.colorScheme.surface)

    Column(columnModifier) {
        AppBarWithState(state, modifier, scrollBehavior, currentScreen, canNavigateBack, navigateUp)

        val internetModifier = if (state?.style == null || state.style == TopBarStyle.Hidden) CFModifier.statusBarsPadding() else CFModifier
        ConnectionStatus(modifier = internetModifier)
    }
}

@Composable
private fun AppBarWithState(
    state: TopBarState?,
    modifier: Modifier = m,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    currentScreen: NavDestination?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val navigateUp = state?.onNavigationBack ?: navigateUp

    logger().i(state.toString())

    val visibilityState: TopBarVisibilityState = koinInject()
    val isVisible = visibilityState.resolve(currentScreen)

    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier.statusBarsPadding(),
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        val appBarModifier = m

        if (state?.isLoading == true) {
            ShimmerNavigatorBarItem()
            return@AnimatedVisibility
        }

        Surface(
            modifier = appBarModifier,
            shape = RectangleShape,
            color = MaterialTheme.colorScheme.surface
        ) {
            when (state?.style) {
                TopBarStyle.CenterAlignedTopAppBar -> {
                    CenterAlignedTopAppBar(
                        title = { TopBarTitle(state.title ?: TextClause.Empty, state) },
                        modifier = appBarModifier,
                        navigationIcon = { TopBarNavigationIcon(canNavigateBack, state, navigateUp) },
                        actions = { TopBarActions(state) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }

                TopBarStyle.Large -> {
                    LargeTopAppBar(
                        title = { TopBarTitle(state.title ?: TextClause.Empty, state) },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = appBarModifier,
                        navigationIcon = { TopBarNavigationIcon(canNavigateBack, state, navigateUp) },
                        actions = { TopBarActions(state) },
                        scrollBehavior = scrollBehavior
                    )
                }

                TopBarStyle.Medium -> {
                    MediumTopAppBar(
                        title = { TopBarTitle(state.title ?: TextClause.Empty, state) },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = appBarModifier,
                        navigationIcon = { TopBarNavigationIcon(canNavigateBack, state, navigateUp) },
                        actions = { TopBarActions(state) },
                        scrollBehavior = scrollBehavior
                    )
                }

                TopBarStyle.Default -> {
                    TopAppBar(
                        title = { TopBarTitle(state.title ?: TextClause.Empty, state) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = appBarModifier,
                        navigationIcon = { TopBarNavigationIcon(canNavigateBack, state, navigateUp) },
                        actions = { TopBarActions(state) },
                        scrollBehavior = scrollBehavior
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun TopBarActions(state: TopBarState) {
    state.actions.forEach { action ->
        Box {
            action.icon(this)
        }
    }
}

@Composable
private fun TopBarNavigationIcon(canNavigateBack: Boolean, state: TopBarState, navigateUp: () -> Unit) {
    if (canNavigateBack) {
        if (state.backIcon == BackIconState.None) return
        IconButton(onClick = navigateUp) {
            when (state.backIcon) {
                Back -> Icons.AutoMirrored.ArrowBack
                Close -> Icons.Default.Close
                else -> Icons.AutoMirrored.ArrowBack
            }
        }
    }
}

@Composable
private fun TopBarTitle(
    title: TextClause,
    state: TopBarState,
) {
    Row(m.ph(0), verticalAlignment = CenterVertically) {
        if (title.isEmpty()) {
            state.customTitle.invoke()
        } else {
            val style = when (state.titleStyle) {
                TitleStyle.Normal -> bodyMedium
                TitleStyle.Big -> headlineBold
                TitleStyle.Large -> headlineAccent
            }

            state.titleIcon.invoke()

            CFText(
                title, style = style.copy(color = LocalContentColor.current), modifier = CFModifier.weight(1f), overflow = TextOverflow.Ellipsis
            )
        }
    }
}