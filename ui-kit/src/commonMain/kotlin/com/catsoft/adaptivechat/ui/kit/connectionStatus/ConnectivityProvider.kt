package com.catsoft.adaptivechat.ui.kit.connectionStatus

import dev.jordond.connectivity.Connectivity

class ConnectivityProvider() {
    fun provide() : Connectivity {
        return Connectivity {
            autoStart = true
        }
    }
}