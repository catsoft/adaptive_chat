package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Shortcut for [Modifier.padding] with uniform padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.p: Modifier get() = Modifier.padding(all = this.dp)

/**
 * Shortcut for [Modifier.padding] with individual sides in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun p(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Modifier = 
    Modifier.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)

/**
 * Shortcut for [Modifier.padding] with horizontal and vertical padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun p(horizontal: Int, vertical: Int): Modifier = 
    Modifier.padding(horizontal = horizontal.dp, vertical = vertical.dp)

/**
 * Shortcut for [Modifier.padding] with top padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.pt: Modifier get() = Modifier.padding(top = this.dp)

/**
 * Shortcut for [Modifier.padding] with bottom padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.pb: Modifier get() = Modifier.padding(bottom = this.dp)

/**
 * Shortcut for [Modifier.padding] with start padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.ps: Modifier get() = Modifier.padding(start = this.dp)

/**
 * Shortcut for [Modifier.padding] with end padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.pe: Modifier get() = Modifier.padding(end = this.dp)

/**
 * Shortcut for [Modifier.padding] with horizontal padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.ph: Modifier get() = Modifier.padding(horizontal = this.dp)

/**
 * Shortcut for [Modifier.padding] with vertical padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
inline val Int.pv: Modifier get() = Modifier.padding(vertical = this.dp)

/**
 * Shortcut for [Modifier.padding] with uniform padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.p(all: Int): Modifier = this.padding(all = all.dp)

/**
 * Shortcut for [Modifier.padding] with individual sides in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.p(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Modifier = 
    this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)

/**
 * Shortcut for [Modifier.padding] with horizontal and vertical padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.p(horizontal: Int, vertical: Int): Modifier = 
    this.padding(horizontal = horizontal.dp, vertical = vertical.dp)

/**
 * Shortcut for [Modifier.padding] with top padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.pt(top: Int): Modifier = this.padding(top = top.dp)

/**
 * Shortcut for [Modifier.padding] with bottom padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.pb(bottom: Int): Modifier = this.padding(bottom = bottom.dp)

/**
 * Shortcut for [Modifier.padding] with start padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.ps(start: Int): Modifier = this.padding(start = start.dp)

/**
 * Shortcut for [Modifier.padding] with end padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.pe(end: Int): Modifier = this.padding(end = end.dp)

/**
 * Shortcut for [Modifier.padding] with horizontal padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.ph(horizontal: Int): Modifier = this.padding(horizontal = horizontal.dp)

/**
 * Shortcut for [Modifier.padding] with vertical padding in dp
 * @see androidx.compose.foundation.layout.padding
 */
fun Modifier.pv(vertical: Int): Modifier = this.padding(vertical = vertical.dp)
