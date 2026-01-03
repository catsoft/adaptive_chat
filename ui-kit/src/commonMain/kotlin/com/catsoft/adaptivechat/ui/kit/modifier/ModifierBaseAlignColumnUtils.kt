package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// ColumnScope alignment shortcuts

/**
 * Shortcut for [Modifier.align] with [Alignment.Start]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alignStart: Modifier get() = Modifier.align(Alignment.Start)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterHorizontally]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alignCenterHorizontally: Modifier get() = Modifier.align(Alignment.CenterHorizontally)

/**
 * Shortcut for [Modifier.align] with [Alignment.End]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alignEnd: Modifier get() = Modifier.align(Alignment.End)
