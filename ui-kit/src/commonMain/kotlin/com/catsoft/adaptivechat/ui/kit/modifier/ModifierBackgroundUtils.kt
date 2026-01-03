package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

/**
 * Shortcut for [Modifier.background] with color
 * @see androidx.compose.foundation.background
 */
fun Modifier.bg(color: Color, shape: Shape = RectangleShape): Modifier = 
    this.background(color, shape)

/**
 * Shortcut for [Modifier.background] with brush
 * @see androidx.compose.foundation.background
 */
fun Modifier.bg(brush: Brush, shape: Shape = RectangleShape): Modifier = 
    this.background(brush, shape)
