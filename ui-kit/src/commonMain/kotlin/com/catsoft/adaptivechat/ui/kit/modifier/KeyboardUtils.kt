package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
expect fun isKeyboardVisiblePlatform(): State<Boolean>
