package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalDensity
import com.catsoft.adaptivechat.logger.logger

@Composable
actual fun isKeyboardVisiblePlatform(): State<Boolean> {
    val offset = WindowInsets.ime.getBottom(LocalDensity.current)
    logger().i( offset.toString())
    val isImeVisible = offset > 0
    logger().i( isImeVisible.toString())
    return rememberUpdatedState(isImeVisible)
}