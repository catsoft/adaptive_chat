package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// BoxScope alignment shortcuts

/**
 * Shortcut for [Modifier.align] with [Alignment.TopStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alTS: Modifier get() = Modifier.align(Alignment.TopStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.TopCenter]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alTC: Modifier get() = Modifier.align(Alignment.TopCenter)

/**
 * Shortcut for [Modifier.align] with [Alignment.TopEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alTE: Modifier get() = Modifier.align(Alignment.TopEnd)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alCS: Modifier get() = Modifier.align(Alignment.CenterStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.Center]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alC: Modifier get() = Modifier.align(Alignment.Center)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alCE: Modifier get() = Modifier.align(Alignment.CenterEnd)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alBS: Modifier get() = Modifier.align(Alignment.BottomStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomCenter]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alBC: Modifier get() = Modifier.align(Alignment.BottomCenter)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alBE: Modifier get() = Modifier.align(Alignment.BottomEnd)
