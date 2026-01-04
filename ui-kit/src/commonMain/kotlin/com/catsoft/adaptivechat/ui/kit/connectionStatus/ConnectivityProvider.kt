package com.chatfuel.shared.ui.connection

import dev.jordond.connectivity.Connectivity

class ConnectivityProvider() {
    fun provide() : Connectivity {
        return Connectivity {
            autoStart = true
        }
    }
}