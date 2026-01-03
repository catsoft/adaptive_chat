package com.chatfuel.shared.uiKit.utils

import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chatfuel.shared.uiKit.modifier.CFModifier


// CFModifier.align(Alignment.CenterVertically)
inline val RowScope.alignCenterVertically: Modifier get() = CFModifier.align(Alignment.CenterVertically)

// CFModifier.align(Alignment.Top)
inline val RowScope.alignTop: Modifier get() = CFModifier.align(Alignment.Top)

// CFModifier.align(Alignment.Bottom)
inline val RowScope.alignBottom: Modifier get() = CFModifier.align(Alignment.Bottom)
