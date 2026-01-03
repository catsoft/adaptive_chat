package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shortcut for [Modifier.width] in dp
 * @see androidx.compose.foundation.layout.width
 */
inline val Int.w: Modifier get() = Modifier.width(this.dp)

/**
 * Shortcut for [Modifier.height] in dp
 * @see androidx.compose.foundation.layout.height
 */
inline val Int.h: Modifier get() = Modifier.height(this.dp)

/**
 * Shortcut for [Modifier.size] in dp
 * @see androidx.compose.foundation.layout.size
 */
inline val Int.s: Modifier get() = Modifier.size(this.dp)

/**
 * Shortcut for [Modifier.width] in dp
 * @see androidx.compose.foundation.layout.width
 */
fun Modifier.w(width: Int): Modifier = this.width(width.dp)

/**
 * Shortcut for [Modifier.height] in dp
 * @see androidx.compose.foundation.layout.height
 */
fun Modifier.h(height: Int): Modifier = this.height(height.dp)

/**
 * Shortcut for [Modifier.size] in dp
 * @see androidx.compose.foundation.layout.size
 */
fun Modifier.s(size: Int): Modifier = this.size(size.dp)

/**
 * Vertical spacer with height in dp
 * @see androidx.compose.foundation.layout.Spacer
 */
inline val Int.sv @Composable  get() = Spacer(Modifier.height(this.dp))

/**
 * Vertical spacer item for LazyColumn/LazyRow
 * @see androidx.compose.foundation.layout.Spacer
 */
fun LazyListScope.sv(key: String, space: Int) = item(key = key) { space.sv }

/**
 * Horizontal spacer with width in dp
 * @see androidx.compose.foundation.layout.Spacer
 */
inline val Int.sh @Composable  get() = Spacer(Modifier.width(this.dp))

/**
 * Flexible spacer for Row (weight = 1F)
 * @see androidx.compose.foundation.layout.Spacer
 */
inline val RowScope.sf @Composable  get() = Spacer(Modifier.weight(1F))

/**
 * Flexible spacer for Column (weight = 1F)
 * @see androidx.compose.foundation.layout.Spacer
 */
inline val ColumnScope.sf @Composable get() = Spacer(Modifier.weight(1F))

/**
 * Flexible spacer for Row with min constraints
 * @see androidx.compose.foundation.layout.Spacer
 */
@Composable
fun RowScope.sf(
    minWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
) = Spacer(
    Modifier
        .weight(1F)
        .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
)

/**
 * Shortcut for [Modifier.fillMaxWidth]
 * @see androidx.compose.foundation.layout.fillMaxWidth
 */
inline val mw: Modifier get() = Modifier.fillMaxWidth()

/**
 * Shortcut for [Modifier.fillMaxHeight]
 * @see androidx.compose.foundation.layout.fillMaxHeight
 */
inline val mh: Modifier get() = Modifier.fillMaxHeight()

/**
 * Shortcut for [Modifier.fillMaxSize]
 * @see androidx.compose.foundation.layout.fillMaxSize
 */
inline val ms: Modifier get() = Modifier.fillMaxSize()

/**
 * Shortcut for [Modifier.weight] in Row (weight = 1.0F)
 * @see androidx.compose.foundation.layout.RowScope.weight
 */
inline val RowScope.mf: Modifier get() = Modifier.weight(1.0F)

/**
 * Shortcut for [Modifier.weight] in Column (weight = 1.0F)
 * @see androidx.compose.foundation.layout.ColumnScope.weight
 */
inline val ColumnScope.mf: Modifier get() = Modifier.weight(1.0F)

/**
 * Shortcut for [Modifier.fillMaxWidth]
 * @see androidx.compose.foundation.layout.fillMaxWidth
 */
fun Modifier.mw(): Modifier = this.fillMaxWidth()

/**
 * Shortcut for [Modifier.fillMaxHeight]
 * @see androidx.compose.foundation.layout.fillMaxHeight
 */
fun Modifier.mh(): Modifier = this.fillMaxHeight()

/**
 * Shortcut for [Modifier.fillMaxSize]
 * @see androidx.compose.foundation.layout.fillMaxSize
 */
fun Modifier.ms(): Modifier = this.fillMaxSize()

/**
 * Shortcut for creating background modifier from Color
 * @see androidx.compose.foundation.background
 */
fun Color.bg(shape: Shape = RectangleShape): Modifier = Modifier.background(this, shape)
