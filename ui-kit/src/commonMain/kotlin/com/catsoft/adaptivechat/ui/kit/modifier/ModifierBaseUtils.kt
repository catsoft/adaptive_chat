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
import com.chatfuel.shared.uiKit.modifier.CFModifier


// CFModifier.width(this.dp)
inline val Int.w: Modifier get() = CFModifier.width(this.dp)

// CFModifier.height(this.dp)
inline val Int.h: Modifier get() = CFModifier.height(this.dp)

// CFModifier.height(this.dp)
inline val Int.s: Modifier get() = CFModifier.size(this.dp)

// this.width(width.dp)
fun Modifier.w(width: Int): Modifier = this.width(width.dp)

// this.height(height.dp)
fun Modifier.h(height: Int): Modifier = this.height(height.dp)

//  this.size(height.dp)
fun Modifier.s(size: Int): Modifier = this.size(size.dp)


// Spacer(this.dp)
inline val Int.sv: Unit @Composable get() = Spacer(CFModifier.height(this.dp))

fun LazyListScope.sv(key: String, space: Int) = item(key = key) { space.sv }

// Spacer(CFModifier.width(this.dp))
inline val Int.sh: Unit @Composable get() = Spacer(CFModifier.width(this.dp))

// Spacer(CFModifier.weight(1F))
inline val RowScope.sf: Unit @Composable get() = Spacer(CFModifier.weight(1F))

// CFModifier.fillMaxWidth()
inline val mw: Modifier get(): Modifier = CFModifier.fillMaxWidth()

// CFModifier.fillMaxHeight()
inline val mh: Modifier get(): Modifier = CFModifier.fillMaxHeight()

// CFModifier.fillMaxSize()
inline val ms: Modifier get(): Modifier = CFModifier.fillMaxSize()

// CFModifier.weight(1.0F)
inline val RowScope.mf: Modifier get(): Modifier = CFModifier.weight(1.0F)

// CFModifier.weight(1.0F)
inline val ColumnScope.mf: Modifier get(): Modifier = CFModifier.weight(1.0F)

// this.fillMaxWidth()
fun Modifier.mw(): Modifier = this.fillMaxWidth()

// this.fillMaxHeight()
fun Modifier.mh(): Modifier = this.fillMaxHeight()

// this.fillMaxSize()
fun Modifier.ms(): Modifier = this.fillMaxSize()

fun Color.bg(shape: Shape = RectangleShape): Modifier = CFModifier.background(this, shape)

@Composable
// CFModifier.weight(1F).defaultMinSize(minWidth = minWidth, minHeight = minHeight)
fun RowScope.sf(
    minWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
) = Spacer(
    CFModifier
        .weight(1F)
        .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
)

// Spacer(CFModifier.weight(1F))
inline val ColumnScope.sf: Unit @Composable get() = Spacer(CFModifier.weight(1F))
