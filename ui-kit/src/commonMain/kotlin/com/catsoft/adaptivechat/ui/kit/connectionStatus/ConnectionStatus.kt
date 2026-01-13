package com.catsoft.adaptivechat.ui.kit.connectionStatus

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
import com.adaptivechat.localization.resources.no_internet_connection
import com.catsoft.adaptivechat.localization.Strings
import com.catsoft.adaptivechat.ui.kit.api.textClause.s
import com.catsoft.adaptivechat.ui.kit.api.textClause.str
import com.catsoft.adaptivechat.ui.kit.modifier.h
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.mw
import com.catsoft.adaptivechat.ui.kit.modifier.s
import com.catsoft.adaptivechat.ui.kit.modifier.sh
import com.catsoft.adaptivechat.ui.kit.text.ACText
import com.catsoft.adaptivechat.ui.kit.modifier.colors
import com.catsoft.adaptivechat.ui.kit.modifier.typo
import dev.jordond.connectivity.Connectivity
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ConnectionStatus(
    viewModel: ConnectivityViewModel = koinViewModel(),
    modifier: Modifier = m,
) {
    val connected: Connectivity.Status by viewModel.isConnected.collectAsStateWithLifecycle()

    if (connected.isConnected) return

    Column(
        modifier = modifier
            .mw()
            .background(
                colors.errorContainer,
                RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = 48.h
        ) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = Strings.no_internet_connection.s(),
                tint = colors.onErrorContainer,
                modifier = 24.s
            )
            16.sh
            ACText(
                text = Strings.no_internet_connection.str(),
                style = typo.labelLarge.copy(color = colors.onErrorContainer),
            )
        }
    }
}