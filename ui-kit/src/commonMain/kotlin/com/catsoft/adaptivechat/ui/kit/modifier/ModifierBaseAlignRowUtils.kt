package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// RowScope alignment shortcuts

/**
 * Shortcut for [Modifier.align] with [Alignment.CenterVertically]
 * @see androidx.compose.foundation.layout.RowScope.align
 */
inline val RowScope.alCV: Modifier get() = Modifier.align(Alignment.CenterVertically)

/**
 * Shortcut for [Modifier.align] with [Alignment.Top]
 * @see androidx.compose.foundation.layout.RowScope.align
 */
inline val RowScope.alT: Modifier get() = Modifier.align(Alignment.Top)

/**
 * Shortcut for [Modifier.align] with [Alignment.Bottom]
 * @see androidx.compose.foundation.layout.RowScope.align
 */
inline val RowScope.alB: Modifier get() = Modifier.align(Alignment.Bottom)

// Alignment shortcuts

/**
 * Shortcut for [Alignment.TopStart]
 * @see androidx.compose.ui.Alignment.TopStart
 */
inline val alTS: Alignment get() = Alignment.TopStart

/**
 * Shortcut for [Alignment.TopCenter]
 * @see androidx.compose.ui.Alignment.TopCenter
 */
inline val alTC: Alignment get() = Alignment.TopCenter

/**
 * Shortcut for [Alignment.TopEnd]
 * @see androidx.compose.ui.Alignment.TopEnd
 */
inline val alTE: Alignment get() = Alignment.TopEnd

/**
 * Shortcut for [Alignment.CenterStart]
 * @see androidx.compose.ui.Alignment.CenterStart
 */
inline val alCS: Alignment get() = Alignment.CenterStart

/**
 * Shortcut for [Alignment.Center]
 * @see androidx.compose.ui.Alignment.Center
 */
inline val alC: Alignment get() = Alignment.Center

/**
 * Shortcut for [Alignment.CenterEnd]
 * @see androidx.compose.ui.Alignment.CenterEnd
 */
inline val alCE: Alignment get() = Alignment.CenterEnd

/**
 * Shortcut for [Alignment.CenterVertically]
 * @see androidx.compose.ui.Alignment.CenterVertically
 */
inline val alCV: Alignment.Vertical get() = Alignment.CenterVertically

/**
 * Shortcut for [Alignment.BottomStart]
 * @see androidx.compose.ui.Alignment.BottomStart
 */
inline val alBS: Alignment get() = Alignment.BottomStart

/**
 * Shortcut for [Alignment.BottomCenter]
 * @see androidx.compose.ui.Alignment.BottomCenter
 */
inline val alBC: Alignment get() = Alignment.BottomCenter

/**
 * Shortcut for [Alignment.BottomEnd]
 * @see androidx.compose.ui.Alignment.BottomEnd
 */
inline val alBE: Alignment get() = Alignment.BottomEnd

// Arrangement shortcuts

/**
 * Shortcut for [Arrangement.Start]
 * @see androidx.compose.foundation.layout.Arrangement.Start
 */
inline val arS: Arrangement.Horizontal get() = Arrangement.Start

/**
 * Shortcut for [Arrangement.End]
 * @see androidx.compose.foundation.layout.Arrangement.End
 */
inline val arE: Arrangement.Horizontal get() = Arrangement.End

/**
 * Shortcut for [Arrangement.Center]
 * @see androidx.compose.foundation.layout.Arrangement.Center
 */
inline val arC: Arrangement.HorizontalOrVertical get() = Arrangement.Center

/**
 * Shortcut for [Arrangement.SpaceBetween]
 * @see androidx.compose.foundation.layout.Arrangement.SpaceBetween
 */
inline val arSB: Arrangement.HorizontalOrVertical get() = Arrangement.SpaceBetween

/**
 * Shortcut for [Arrangement.SpaceAround]
 * @see androidx.compose.foundation.layout.Arrangement.SpaceAround
 */
inline val arSA: Arrangement.HorizontalOrVertical get() = Arrangement.SpaceAround

/**
 * Shortcut for [Arrangement.SpaceEvenly]
 * @see androidx.compose.foundation.layout.Arrangement.SpaceEvenly
 */
inline val arSE: Arrangement.HorizontalOrVertical get() = Arrangement.SpaceEvenly

/**
 * Shortcut for [Arrangement.Top]
 * @see androidx.compose.foundation.layout.Arrangement.Top
 */
inline val arT: Arrangement.Vertical get() = Arrangement.Top

/**
 * Shortcut for [Arrangement.Bottom]
 * @see androidx.compose.foundation.layout.Arrangement.Bottom
 */
inline val arB: Arrangement.Vertical get() = Arrangement.Bottom
