package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import com.catsoft.adaptivechat.logger.logger
import com.catsoft.adaptivechat.logger.runLogCatching


fun Modifier.clipToBounds(clip: Boolean) = this.graphicsLayer(clip = clip)

fun Modifier.roundCorners(radius: Dp) = this.graphicsLayer(clip = true, shape = RoundedCornerShape(radius))


@Composable
fun Modifier.clearFocusOnTap(): Modifier {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    return this.clickable(
        interactionSource = interactionSource,
        indication = null // No ripple effect
    ) {
        logger().i("Clear focus on tap")
        runLogCatching(block = {
            focusManager.clearFocus(force = true)
        }, onError = {
            runLogCatching(block = {
                focusManager.clearFocus(force = false)
            })
        })
    }
}

@Stable
fun Modifier.mirror(): Modifier = composed {
    graphicsLayer {
        rotationY = 180f
    }
}