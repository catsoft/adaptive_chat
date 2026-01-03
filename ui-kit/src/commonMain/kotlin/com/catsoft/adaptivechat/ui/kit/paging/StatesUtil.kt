package com.catsoft.adaptivechat.ui.kit.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.paging.ItemSnapshotList
import app.cash.paging.LoadStateError
import app.cash.paging.compose.LazyPagingItems
import com.chatfuel.common.logger

@Composable
fun OnCreate(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_CREATE) {
    block()
}

@Composable
fun OnStart(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_START) {
    block()
}

@Composable
fun OnResumeWithSkip(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_RESUME) {
    block()
}

@Composable
fun OnResume(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_RESUME) {
    block()
}

@Composable
fun OnStop(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_STOP) {
    block()
}

@Composable
fun OnDestroy(block: suspend () -> Unit) = OnEventWithState(Lifecycle.Event.ON_DESTROY) {
    block()
}

@Composable
private fun OnEventWithState(requiredEvent: Lifecycle.Event, block: suspend () -> Unit) {
    var lifecycleEvent by remember { mutableStateOf(requiredEvent) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == requiredEvent) {
            block()
        }
    }
}