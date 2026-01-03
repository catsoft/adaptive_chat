package com.chatfuel.shared.uiKit.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

// scope extensions
fun completedLambda(): () -> Job = { Job().apply { complete() } }

fun <T> completedOneLambda(): (T) -> Job = { Job().apply { complete() } }

fun <T, B> completedPairLambda(): (T, B) -> Job = { _, _ -> Job().apply { complete() } }

fun <T, B, E> completedTripletLambda(): (T, B, E) -> Job = { _, _, _ -> Job().apply { complete() } }

fun completedJob(): Job = Job().apply { complete() }

fun CoroutineScope.infiniteJob(): Job = launch {
    while (isActive) {
    }
}

fun CoroutineScope.delayedJob(delayMillis: Long, action: suspend () -> Unit = { }): Job = launch {
    delay(delayMillis)
    action()
}

fun CoroutineScope.delayedShortJob(action: suspend () -> Unit = { }): Job = launch {
    delay(delayShortJobDelay)
    action()
}

fun CoroutineScope.delayedNormalJob(action: suspend () -> Unit = { }): Job = launch {
    delay(delayNormalJobDelay)
    action()
}

fun CoroutineScope.delayedLongJob(action: suspend () -> Unit = { }): Job = launch {
    delay(delayLongJobDelay)
    action()
}

fun CoroutineScope.actionJob(action: suspend () -> Unit = { }): Job = launch {
    action()
}

// global extensions
@OptIn(DelicateCoroutinesApi::class)
fun infiniteGlobalJob(): Job = GlobalScope.launch {
    while (isActive) {
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun delayedGlobalJob(delayMillis: Long, action: suspend () -> Unit): Job = GlobalScope.launch {
    delay(delayMillis)
    action()
}

@OptIn(DelicateCoroutinesApi::class)
fun delayedShortGlobalJob(action: suspend () -> Unit): Job = GlobalScope.launch {
    delay(delayShortJobDelay)
    action()
}

@OptIn(DelicateCoroutinesApi::class)
fun delayedNormalGlobalJob(action: suspend () -> Unit): Job = GlobalScope.launch {
    delay(delayNormalJobDelay)
    action()
}

@OptIn(DelicateCoroutinesApi::class)
fun delayedLongGlobalJob(action: suspend () -> Unit): Job = GlobalScope.launch {
    delay(delayLongJobDelay)
    action()
}

@OptIn(DelicateCoroutinesApi::class)
fun actionGlobalJob(action: suspend () -> Unit): Job = GlobalScope.launch {
    action()
}


// viewmodel extensions
fun ViewModel.infiniteJob(): Job = viewModelScope.launch {
    while (isActive) {
    }
}

fun ViewModel.delayedJob(delayMillis: Long, action: suspend () -> Unit = { }): Job = viewModelScope.launch {
    delay(delayMillis)
    action()
}

fun ViewModel.delayedShortJob(action: suspend () -> Unit = { }): Job = viewModelScope.launch {
    delay(delayShortJobDelay)
    action()
}

fun ViewModel.delayedNormalJob(action: suspend () -> Unit = { }): Job = viewModelScope.launch {
    delay(delayNormalJobDelay)
    action()
}

fun ViewModel.delayedLongJob(action: suspend () -> Unit = { }): Job = viewModelScope.launch {
    delay(delayLongJobDelay)
    action()
}

fun ViewModel.actionJob(action: suspend () -> Unit): Job = viewModelScope.launch {
    action()
}

const val delayShortJobDelay = 400L
const val delayNormalJobDelay = 800L
const val delayLongJobDelay = 1600L
