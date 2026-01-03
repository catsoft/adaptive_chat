package com.chatfuel.shared.uiKit.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// RoundedCornerShape(this.dp)
inline val Int.rc: RoundedCornerShape get() = RoundedCornerShape(this.dp)


// RoundedCornerShape(topStart.dp, topEnd.dp, bottomEnd.dp, bottomStart.dp)
@Composable
fun rc(
    topStart: Int = 0,
    topEnd: Int = 0,
    bottomEnd: Int = 0,
    bottomStart: Int = 0
): RoundedCornerShape = RoundedCornerShape(
    topStart.dp, topEnd.dp, bottomEnd.dp, bottomStart.dp
)