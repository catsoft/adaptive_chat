package com.chatfuel.shared.uiKit.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chatfuel.shared.uiKit.modifier.CFModifier

// CFModifier.align(Alignment.Start)
inline val ColumnScope.alignStart: Modifier get() = CFModifier.align(Alignment.Start)

// CFModifier.align(Alignment.CenterHorizontally)
inline val ColumnScope.alignCenterHorizontally: Modifier get() = CFModifier.align(Alignment.CenterHorizontally)

// CFModifier.align(Alignment.End)
inline val ColumnScope.alignEnd: Modifier get() = CFModifier.align(Alignment.End)