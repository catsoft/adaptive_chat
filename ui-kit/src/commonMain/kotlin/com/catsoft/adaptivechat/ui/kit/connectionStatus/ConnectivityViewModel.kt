package com.chatfuel.shared.ui.connection

import androidx.lifecycle.viewModelScope
import com.chatfuel.shared.viewModels.CFViewModelUnit
import com.chatfuel.shared.viewModels.VMDependencies
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ConnectivityViewModel(
    override val dep: VMDependencies,
    private val connectivity: Connectivity,
) : CFViewModelUnit() {

    val isConnected = connectivity.statusUpdates.stateIn(this.viewModelScope, SharingStarted.Lazily, Connectivity.Status.Connected(metered = false))
}

