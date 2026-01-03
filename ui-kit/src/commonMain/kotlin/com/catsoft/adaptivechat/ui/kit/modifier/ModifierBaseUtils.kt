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


// Modifier.width(this.dp)
inline val Int.w: Modifier get() = Modifier.width(this.dp)

// Modifier.height(this.dp)
inline val Int.h: Modifier get() = Modifier.height(this.dp)

// Modifier.height(this.dp)
inline val Int.s: Modifier get() = Modifier.size(this.dp)

// this.width(width.dp)
fun Modifier.w(width: Int): Modifier = this.width(width.dp)

// this.height(height.dp)
fun Modifier.h(height: Int): Modifier = this.height(height.dp)

//  this.size(height.dp)
fun Modifier.s(size: Int): Modifier = this.size(size.dp)


// Spacer(this.dp)
inline val Int.sv: Unit @Composable get() = Spacer(Modifier.height(this.dp))

fun LazyListScope.sv(key: String, space: Int) = item(key = key) { space.sv }

// Spacer(Modifier.width(this.dp))
inline val Int.sh: Unit @Composable get() = Spacer(Modifier.width(this.dp))

// Spacer(Modifier.weight(1F))
inline val RowScope.sf: Unit @Composable get() = Spacer(Modifier.weight(1F))

// Modifier.fillMaxWidth()
inline val mw: Modifier get(): Modifier = Modifier.fillMaxWidth()

// Modifier.fillMaxHeight()
inline val mh: Modifier get(): Modifier = Modifier.fillMaxHeight()

// Modifier.fillMaxSize()
inline val ms: Modifier get(): Modifier = Modifier.fillMaxSize()

// Modifier.weight(1.0F)
inline val RowScope.mf: Modifier get(): Modifier = Modifier.weight(1.0F)

// Modifier.weight(1.0F)
inline val ColumnScope.mf: Modifier get(): Modifier = Modifier.weight(1.0F)

// this.fillMaxWidth()
fun Modifier.mw(): Modifier = this.fillMaxWidth()

// this.fillMaxHeight()
fun Modifier.mh(): Modifier = this.fillMaxHeight()

// this.fillMaxSize()
fun Modifier.ms(): Modifier = this.fillMaxSize()

fun Color.bg(shape: Shape = RectangleShape): Modifier = Modifier.background(this, shape)

@Composable
// Modifier.weight(1F).defaultMinSize(minWidth = minWidth, minHeight = minHeight)
fun RowScope.sf(
    minWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
) = Spacer(
    Modifier
        .weight(1F)
        .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
)

// Spacer(Modifier.weight(1F))
inline val ColumnScope.sf: Unit @Composable get() = Spacer(Modifier.weight(1F))
