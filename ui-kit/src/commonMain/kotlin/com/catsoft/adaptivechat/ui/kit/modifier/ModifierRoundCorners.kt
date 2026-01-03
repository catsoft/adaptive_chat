package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * Shortcut for [RoundedCornerShape] with uniform radius
 * @see androidx.compose.foundation.shape.RoundedCornerShape
 */
inline val Int.rc: RoundedCornerShape get() = RoundedCornerShape(this.dp)

/**
 * Shortcut for [RoundedCornerShape] with individual corner radii
 * @see androidx.compose.foundation.shape.RoundedCornerShape
 */
fun rc(
    topStart: Int = 0,
    topEnd: Int = 0,
    bottomEnd: Int = 0,
    bottomStart: Int = 0
): RoundedCornerShape = RoundedCornerShape(
    topStart.dp, topEnd.dp, bottomEnd.dp, bottomStart.dp
)
