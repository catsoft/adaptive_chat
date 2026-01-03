package com.chatfuel.shared.uiKit.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class LineOrientation { TOP, BOTTOM, START, END }

fun DrawScope.drawGridLine(
    orientation: LineOrientation,
    color: Color,
    strokeWidth: Dp = 0.5.dp,
    pathEffect: PathEffect? = null
) {
    val (start, end) = when (orientation) {
        LineOrientation.TOP -> Offset(0f, 0f) to Offset(size.width, 0f)
        LineOrientation.BOTTOM -> Offset(0f, size.height) to Offset(size.width, size.height)
        LineOrientation.START -> Offset(0f, 0f) to Offset(0f, size.height)
        LineOrientation.END -> Offset(size.width, 0f) to Offset(size.width, size.height)
    }
    drawLine(color, start, end, strokeWidth.toPx(), pathEffect = pathEffect)
}
