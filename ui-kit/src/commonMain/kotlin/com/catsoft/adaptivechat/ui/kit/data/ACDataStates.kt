package com.catsoft.adaptivechat.ui.kit.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catsoft.adaptivechat.ui.kit.api.data.ACData
import com.chatfuel.common.timber
import com.chatfuel.shared.uiKit.modifier.CFModifier
import com.catsoft.adaptivechat.ui.kit.screen.states.ui.CFErrorDefaultByThrowable
import com.catsoft.adaptivechat.ui.kit.screen.states.ui.CFLoaderDefault
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState
import com.catsoft.adaptivechat.ui.kit.topBar.TopBarState.Companion.setLoading
import com.chatfuel.shared_api.data.CFData

fun <T> LazyListScope.mapStates(
    data: ACData<T>,
    loading: @Composable LazyItemScope.(Modifier) -> Unit = { CFLoaderDefault(it) },
    error: @Composable LazyItemScope.(Throwable, Modifier) -> Unit = { error, modifier -> CFErrorDefaultByThrowable(error, modifier) },
    content: LazyListScope.(T) -> Unit
) {
    timber().i { "mapStates: $data" }
    when {
        data.isLoading -> {
            item("loading") {
                loading(CFModifier.fillParentMaxSize())
            }
        }

        data.error != null -> {
            item("error") {
                data.error?.let { error(it, CFModifier.fillParentMaxSize()) }
            }
        }

        else -> {
            data.content?.let { content(it) }
        }
    }
}

@Composable
fun <T> ColumnScope.mapStates(
    data: CFData<T>,
    loading: @Composable ColumnScope.(Modifier) -> Unit = { CFLoaderDefault(it) },
    error: @Composable ColumnScope.(Throwable, Modifier) -> Unit = { error, modifier -> CFErrorDefaultByThrowable(error, modifier) },
    content: @Composable ColumnScope.(T) -> Unit
) {
    timber().i { "mapStates: $data" }
    Column {
        when {
            data.isLoading -> {
                loading(CFModifier)
            }

            data.error != null -> {
                data.error?.let { error(it, CFModifier) }
            }

            else -> {
                data.content?.let { content(it) }
            }
        }
    }
}

@Composable
fun <T> BoxScope.mapStates(
    data: CFData<T>,
    loading: @Composable BoxScope.(Modifier) -> Unit = { CFLoaderDefault(it) },
    error: @Composable BoxScope.(Throwable, Modifier) -> Unit = { error, modifier -> CFErrorDefaultByThrowable(error, modifier) },
    content: @Composable BoxScope.(T) -> Unit
) {
    timber().i { "mapStates: $data" }
    Box {
        when {
            data.isLoading -> {
                loading(CFModifier)
            }

            data.error != null -> {
                data.error?.let { error(it, CFModifier) }
            }

            else -> {
                data.content?.let { content(it) }
            }
        }
    }
}

@Composable
fun <T> mapAppBar(
    state: CFData<T>,
    contentBar: (T) -> TopBarState
): TopBarState {
    when {
        state.isLoading -> return TopBarState.plainTitle("".raw()).setLoading(true)
        state.error != null -> return TopBarState.plainTitle("".raw()).setLoading(true)
        else -> return contentBar(state.content!!)
    }
}