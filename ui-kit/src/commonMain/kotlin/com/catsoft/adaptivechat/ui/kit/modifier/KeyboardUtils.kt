package com.chatfuel.shared.uiKit.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
expect fun isKeyboardVisiblePlatform(): State<Boolean>
