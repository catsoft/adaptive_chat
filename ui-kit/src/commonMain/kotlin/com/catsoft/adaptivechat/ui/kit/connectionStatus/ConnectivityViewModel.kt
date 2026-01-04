package com.catsoft.adaptivechat.ui.kit.connectionStatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ConnectivityViewModel(
    private val connectivity: Connectivity,
) : ViewModel() {

    val isConnected = connectivity.statusUpdates.stateIn(this.viewModelScope, SharingStarted.Lazily, Connectivity.Status.Connected(metered = false))
}

