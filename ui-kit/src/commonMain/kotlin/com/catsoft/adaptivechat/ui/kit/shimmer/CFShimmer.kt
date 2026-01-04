package com.chatfuel.shared.uiKit.shimmer

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chatfuel.palette.neutral_50
import com.chatfuel.shared.uiKit.modifier.m
import com.chatfuel.shared.uiKit.utils.h
import com.chatfuel.shared.uiKit.utils.mw
import com.chatfuel.shared.uiKit.utils.pe
import com.chatfuel.shared.uiKit.utils.roundCorners
import com.chatfuel.shared.uiKit.utils.s
import com.chatfuel.shared.uiKit.utils.sv
import com.valentinilk.shimmer.shimmer

val shimmerColor: Color
    get() = neutral_50

@Composable
fun ShimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

@Composable
fun CFShimmer(modifier: Modifier = m) {
    Column(
        modifier = modifier
            .shimmer()
            .fillMaxWidth(),
    ) {
        Column(
            m.padding(16.dp, 0.dp),
        ) {
            ShimmerDialogListItem()

            ShimmerDialogListItem()

            ShimmerDialogListItem()
        }
    }
}


@Composable
fun ShimmerListItem() {
    Row(
        modifier = m
            .fillMaxWidth()
            .height(56.dp)
            .shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(56.s, contentAlignment = Alignment.Center) {
            ShimmerBox(size = 24, corners = 12)
        }

        Column(
            m
                .weight(1f)
                .padding(top = 8.dp, end = 16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ShimmerBoxHorizontal(height = 12, corners = 8, modifier = m.fillMaxWidth(0.2F))

            8.sv

            ShimmerBoxHorizontal(height = 12, corners = 8, modifier = m.fillMaxWidth(0.4F))
        }
    }
}

@Composable
fun ShimmerDialogListItem() {
    Row(
        modifier = m
            .fillMaxWidth()
            .height(68.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerBox(size = 44, corners = 22)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ShimmerBoxHorizontal(height = 12, corners = 8, modifier = m.fillMaxWidth(0.6F))

            ShimmerBoxHorizontal(
                height = 12, corners = 8, modifier = m
                    .fillMaxWidth()
                    .pe(28)
            )
        }
    }
}

@Composable
fun ShimmerChipsItems(chipsCount: Int = 2, modifier: Modifier = m) {
    Row(
        modifier = modifier.mw().h(64),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until chipsCount) {
            ShimmerBox(height = 82, width = 32, corners = 16)
        }
    }
}

@Composable
fun ShimmerInputItem() {
    Row(
        modifier = m
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 16.dp, 0.dp)
            .shimmer()
            .height(60.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerBoxHorizontal(height = 40, corners = 20, modifier = m.weight(1F))

        ShimmerBox(size = 40, corners = 20)
    }
}

@Composable
fun ShimmerNavigatorBarItem() {
    Row(
        modifier = mw
            .height(64.dp)
            .shimmer()
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerBox(size = 44, corners = 22)

        Column {
            ShimmerBoxHorizontal(height = 12, corners = 8, modifier = m.fillMaxWidth(0.66F))

            8.sv

            ShimmerBoxHorizontal(height = 12, corners = 8, modifier = m.fillMaxWidth(0.51F))
        }
    }
}

@Composable
fun ShimmerBoxHorizontal(height: Int, corners: Int = Int.MAX_VALUE, modifier: Modifier = m) {
    Box(
        modifier = modifier
            .height(height.dp)
            .roundCorners(corners.dp)
            .background(shimmerColor),
    )
}

@Composable
fun ShimmerBoxVertical(width: Int, corners: Int = Int.MAX_VALUE, modifier: Modifier = m) {
    Box(
        modifier = modifier
            .width(width.dp)
            .roundCorners(corners.dp)
            .background(shimmerColor),
    )
}

@Composable
fun ShimmerBox(size: Int, corners: Int = Int.MAX_VALUE, modifier: Modifier = m) {
    ShimmerBox(size, size, corners, modifier)
}

@Composable
fun ShimmerBox(height: Int, width: Int, corners: Int = Int.MAX_VALUE, modifier: Modifier = m) {
    Box(
        modifier = modifier
            .size(height.dp, width.dp)
            .roundCorners(corners.dp)
            .background(shimmerColor),
    )
}