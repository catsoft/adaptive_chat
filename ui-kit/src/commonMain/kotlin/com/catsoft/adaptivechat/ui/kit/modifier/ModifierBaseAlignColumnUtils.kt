package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// Modifier.align(Alignment.Start)
inline val ColumnScope.alignStart: Modifier get() = Modifier.align(Alignment.Start)

// Modifier.align(Alignment.CenterHorizontally)
inline val ColumnScope.alignCenterHorizontally: Modifier get() = Modifier.align(Alignment.CenterHorizontally)

// Modifier.align(Alignment.End)
inline val ColumnScope.alignEnd: Modifier get() = Modifier.align(Alignment.End)