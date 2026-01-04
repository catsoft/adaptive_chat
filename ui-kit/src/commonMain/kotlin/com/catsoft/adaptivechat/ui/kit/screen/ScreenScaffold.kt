package com.catsoft.adaptivechat.ui.kit.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chatfuel.palette.neutral_100
import com.chatfuel.palette.primary_10
import com.chatfuel.shared.uiKit.modifier.CFModifier
import com.chatfuel.shared.uiKit.screens.ui.ScreenScaffoldConfig
import com.catsoft.adaptivechat.ui.kit.topBar.ChatfuelScreenTopBar
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState
import com.catsoft.adaptivechat.ui.kit.topBar.states.TopBarScrollBehavior
import com.chatfuel.shared.uiKit.utils.alignTopCenter
import com.chatfuel.shared.uiKit.utils.ms
import com.chatfuel.shared.uiKit.utils.sv
import com.chatfuel.shared.viewModels.CFViewModel
import com.chatfuel.shared_api.ui.delegates.StateComposableContent

typealias TopBarStateComposableContent<T> = @Composable (T) -> TopBarState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> LazyScreenScaffold(
    viewModel: CFViewModel<T>,
    topBarState: TopBarStateComposableContent<T>,
    scaffoldConfig: ScreenScaffoldConfig,
    modifier: Modifier = ms,
    snackbarHost: StateComposableContent<T> = {},
    floatingActionButton: StateComposableContent<T> = {},
    footer: (@Composable ColumnScope.(T) -> Unit)? = null,
    content: LazyListScope.(T) -> Unit
) {
    val listState = rememberLazyListState()

    BaseScreenScaffold(
        viewModel = viewModel,
        state = topBarState,
        config = scaffoldConfig.copy(withScroll = false, withBottomOffset = false),
        modifier = modifier,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton
    ) { dataState ->
        LazyColumn(CFModifier.weight(1F), state = listState) {
            content(dataState)

            item("lazy_column_bottom_offset") {
                if (scaffoldConfig.withBottomOffset) {
                    72.sv
                }
            }
        }

        footer?.invoke(this, dataState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> ColumnScreenScaffold(
    viewModel: CFViewModel<T>,
    state: TopBarStateComposableContent<T>,
    config: ScreenScaffoldConfig,
    modifier: Modifier = ms,
    snackbarHost: StateComposableContent<T> = {},
    floatingActionButton: StateComposableContent<T> = {},
    content: @Composable ColumnScope.(T) -> Unit
) {
    BaseScreenScaffold(
        viewModel = viewModel,
        state = state,
        config = config,
        modifier = modifier,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton
    ) { dataState ->
        content(dataState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> BoxScreenScaffold(
    viewModel: CFViewModel<T>,
    state: TopBarStateComposableContent<T>,
    config: ScreenScaffoldConfig,
    modifier: Modifier = ms,
    snackbarHost: StateComposableContent<T> = {},
    floatingActionButton: StateComposableContent<T> = {},
    content: @Composable BoxScope.(T) -> Unit
) {
    BaseScreenScaffold(
        viewModel = viewModel,
        state = state,
        config = config,
        modifier = modifier,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton
    ) { dataState ->
        Box(ms) {
            content(dataState)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun <T> BaseScreenScaffold(
    viewModel: CFViewModel<T>,
    state: TopBarStateComposableContent<T>,
    config: ScreenScaffoldConfig,
    modifier: Modifier,
    snackbarHost: StateComposableContent<T>,
    floatingActionButton: StateComposableContent<T>,
    content: @Composable ColumnScope.(T) -> Unit
) {
    val dataState by viewModel.dataStateFlow.collectAsStateWithLifecycle()

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.onRefresh {
                isRefreshing = false
            }
        },
        refreshThreshold = 80.dp
    )

    val m3ScrollBehavior = when (config.scrollBehavior) {
        TopBarScrollBehavior.None -> null
        TopBarScrollBehavior.Defaults -> TopAppBarDefaults.enterAlwaysScrollBehavior()
        TopBarScrollBehavior.EnterAlwaysScrollBehavior -> TopAppBarDefaults.enterAlwaysScrollBehavior()
        TopBarScrollBehavior.ExitUntilCollapsedScrollBehavior -> TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        TopBarScrollBehavior.PinnedScrollBehavior -> TopAppBarDefaults.pinnedScrollBehavior()
    }

    val scrollState = rememberScrollState()

    val bottomPaddingModifier = if (config.withBottomNavigation) CFModifier else CFModifier.navigationBarsPadding()

    Scaffold(
        modifier = modifier.then(bottomPaddingModifier),
        topBar = {
            ChatfuelScreenTopBar(
                state = state(dataState),
                scrollBehavior = m3ScrollBehavior
            )
        },
        snackbarHost = { snackbarHost(dataState) },
        floatingActionButton = { floatingActionButton(dataState) },
        containerColor = neutral_100,
        contentWindowInsets = WindowInsets.ime
    ) { innerPadding ->
        Box(
            ms.padding(
                PaddingValues(
                    start = innerPadding.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    bottom = 0.dp
                )
            )
        ) {
            Column(
                ms
                    .then(m3ScrollBehavior?.nestedScrollConnection?.let { Modifier.nestedScroll(it) } ?: Modifier)
                    .then(if (config.withScroll) Modifier.verticalScroll(scrollState) else modifier)
                    .pullRefresh(pullRefreshState)) {

                content(dataState)

                if (config.withBottomOffset) {
                    72.sv
                }
            }

            if (config.withRefreshing) {
                PullRefreshIndicator(
                    isRefreshing,
                    pullRefreshState,
                    alignTopCenter,
                    contentColor = primary_10
                )
            }
        }
    }
}