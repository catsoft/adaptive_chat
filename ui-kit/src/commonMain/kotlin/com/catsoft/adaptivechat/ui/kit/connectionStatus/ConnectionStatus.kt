package com.chatfuel.shared.ui.connection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chatfuel.palette.accent5_20
import com.chatfuel.palette.neutral_100
import com.chatfuel.shared.compose.typography.bodyBold
import com.chatfuel.shared.localization.Strings
import com.chatfuel.shared.resources.no_internet_connection
import com.chatfuel.shared.uiKit.clause.CFText
import com.chatfuel.shared.uiKit.modifier.CFModifier
import com.chatfuel.shared.uiKit.utils.h
import com.chatfuel.shared.uiKit.utils.mw
import com.chatfuel.shared.uiKit.utils.s
import com.chatfuel.shared.uiKit.utils.sh
import com.chatfuel.shared_api.data.text.s
import com.chatfuel.shared_api.data.text.str
import dev.jordond.connectivity.Connectivity
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ConnectionStatus(
    viewModel: ConnectivityViewModel = koinViewModel(),
    modifier: Modifier = CFModifier,
) {
    val connected: Connectivity.Status by viewModel.isConnected.collectAsStateWithLifecycle()

    if (connected.isConnected) return

    Column(
        modifier = modifier
            .mw()
            .background(
                accent5_20, RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = 48.h
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = Strings.no_internet_connection.s(),
                tint = neutral_100,
                modifier = 24.s
            )
            16.sh
            CFText(Strings.no_internet_connection.str(), bodyBold.copy(color = neutral_100))
        }
    }
}