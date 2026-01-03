package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import com.catsoft.adaptivechat.logger.logger


fun Modifier.clipToBounds(clip: Boolean) = this.graphicsLayer(clip = clip)

fun Modifier.roundCorners(radius: Dp) = this.graphicsLayer(clip = true, shape = RoundedCornerShape(radius))


fun Modifier.gray(enabled: Boolean, disabledTint: Color = neutral_100) = this.then(
    if (enabled) Modifier
    else Modifier.drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(disabledTint, blendMode = BlendMode.Saturation)
        }
    })

fun Modifier.tint(enabled: Boolean, disabledTint: Color = neutral_100) = this.then(
    if (enabled) Modifier
    else Modifier.drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(disabledTint, blendMode = BlendMode.Hue)
        }
    })

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