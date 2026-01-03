package com.catsoft.adaptivechat.ui.kit.api.delegates

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

typealias ComposableContent = @Composable () -> Unit

typealias ComposableWithModifier = @Composable (modifier: Modifier) -> Unit

typealias IconComposable = @Composable (modifier: Modifier, tint: Color) -> Unit

typealias BoxScopeComposable = @Composable BoxScope.() -> Unit

typealias ColumnScopeComposable = @Composable ColumnScope.() -> Unit

typealias LazyScopeComposable = LazyListScope.() -> Unit

typealias RowScopeComposable = @Composable RowScope.() -> Unit

typealias LoadingComposable = @Composable (
    isLoading: Boolean,
    modifier: Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) -> Unit

typealias ItemComposable<T> = @Composable (item: T, modifier: Modifier) -> Unit

typealias StateComposableContent<T> = @Composable T.() -> Unit