package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


// Modifier.align(Alignment.CenterVertically)
inline val RowScope.alignCenterVertically: Modifier get() = Modifier.align(Alignment.CenterVertically)

// Modifier.align(Alignment.Top)
inline val RowScope.alignTop: Modifier get() = Modifier.align(Alignment.Top)

// Modifier.align(Alignment.Bottom)
inline val RowScope.alignBottom: Modifier get() = Modifier.align(Alignment.Bottom)
