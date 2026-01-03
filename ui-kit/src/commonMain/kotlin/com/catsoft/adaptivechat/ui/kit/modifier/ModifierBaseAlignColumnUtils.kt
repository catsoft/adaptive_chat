package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// ColumnScope alignment shortcuts

/**
 * Shortcut for [Modifier.align] with [Alignment.Start]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alS: Modifier get() = Modifier.align(Alignment.Start)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterHorizontally]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alCH: Modifier get() = Modifier.align(Alignment.CenterHorizontally)

/**
 * Shortcut for [Modifier.align] with [Alignment.End]
 * @see androidx.compose.foundation.layout.ColumnScope.align
 */
inline val ColumnScope.alE: Modifier get() = Modifier.align(Alignment.End)
