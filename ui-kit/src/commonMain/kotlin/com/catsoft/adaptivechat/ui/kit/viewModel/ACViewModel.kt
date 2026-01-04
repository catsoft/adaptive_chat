package com.catsoft.adaptivechat.ui.kit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catsoft.adaptivechat.logger.logger
import com.catsoft.adaptivechat.logger.runLogCatching
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.concurrent.atomics.ExperimentalAtomicApi

abstract class ACViewModel<T>() : ViewModel() {

    @OptIn(ExperimentalAtomicApi::class)
    private var loaded: kotlinx.atomicfu.AtomicBoolean = atomic(false)


    // should be above init in viewModel
    abstract fun defaultState(): T

    private val _dataState = MutableStateFlow(defaultState())
    val dataStateFlow: StateFlow<T> by lazy {
        _dataState
            .onStart { onRefresh() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), defaultState()).also {
                subscribeOnScreenData(it)
            }
    }

    protected open suspend fun loadInitialState() {
        // Default implementation does nothing
        // Subclasses override to load their specific data
    }


    val dataState: T
        get() = _dataState.value ?: defaultState()

    init {
        logger().i(this::class.simpleName.orEmpty() + " created")
    }

    private fun subscribeOnScreenData(flow: StateFlow<T?>) {
        this@ACViewModel.viewModelScope.launch {
            flow.collectLatest {
                runLogCatching {
                    logger().i("data state changed: ${this@ACViewModel::class.simpleName.orEmpty()} $it")
                }
            }
        }
    }

    open fun onRefresh(onEnd: () -> Unit = {}) = viewModelScope.launch {
        try {
            if (loaded.value) {
                withTimeoutOrNull(5000L) {
                    loadInitialState()
                }
            } else {
                loaded.value = true
                loadInitialState()
            }
        } catch (e: Throwable) {
            logger().e("Error during onRefresh", e)
        }
        delay(800L)
        onEnd()
    }

    fun onEvent(event: Any) {
        // Default implementation does nothing
        // Subclasses can override to handle events
        logger().i { "Event started: $event" }
        try {
            onEventInternal(event)
        } catch (e: Throwable) {
            logger().e(e) { "Error during onEvent: $event" }
        }
    }

    protected open fun onEventInternal(event: Any) {}


    fun update(updateState: T.() -> T) {
        try {
            _dataState.update { currentState ->
                currentState.updateState()
            }
        } catch (e: Throwable) {
            logger().e(e) { "Data state wasn't updated" }
        }
    }
}
