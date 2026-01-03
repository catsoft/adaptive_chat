package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// BoxScope alignment shortcuts

/**
 * Shortcut for [Modifier.align] with [Alignment.TopStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignTopStart: Modifier get() = Modifier.align(Alignment.TopStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.TopCenter]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignTopCenter: Modifier get() = Modifier.align(Alignment.TopCenter)

/**
 * Shortcut for [Modifier.align] with [Alignment.TopEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignTopEnd: Modifier get() = Modifier.align(Alignment.TopEnd)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignCenterStart: Modifier get() = Modifier.align(Alignment.CenterStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.Center]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignCenter: Modifier get() = Modifier.align(Alignment.Center)

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignCenterEnd: Modifier get() = Modifier.align(Alignment.CenterEnd)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomStart]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignBottomStart: Modifier get() = Modifier.align(Alignment.BottomStart)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomCenter]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignBottomCenter: Modifier get() = Modifier.align(Alignment.BottomCenter)

/**
 * Shortcut for [Modifier.align] with [Alignment.BottomEnd]
 * @see androidx.compose.foundation.layout.BoxScope.align
 */
inline val BoxScope.alignBottomEnd: Modifier get() = Modifier.align(Alignment.BottomEnd)
