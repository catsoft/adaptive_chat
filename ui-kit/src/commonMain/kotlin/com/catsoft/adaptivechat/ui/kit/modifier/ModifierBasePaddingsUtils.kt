package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chatfuel.shared.uiKit.modifier.CFModifier

// CFModifier.padding(all = this.dp)
inline val Int.p: Modifier get() = CFModifier.padding(all = this.dp)

// this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)
fun p(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Modifier = CFModifier.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)

// this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)
fun p(horizontal: Int, vertical: Int): Modifier = CFModifier.padding(horizontal = horizontal.dp, vertical = vertical.dp)

// CFModifier.padding(top = this.dp)
inline val Int.pt: Modifier get() = CFModifier.padding(top = this.dp)

// CFModifier.padding(bottom = this.dp)
inline val Int.pb: Modifier get() = CFModifier.padding(bottom = this.dp)

// CFModifier.padding(start = this.dp)
inline val Int.ps: Modifier get() = CFModifier.padding(start = this.dp)

// CFModifier.padding(end = this.dp)
inline val Int.pe: Modifier get() = CFModifier.padding(end = this.dp)

// CFModifier.padding(horizontal = this.dp)
inline val Int.ph: Modifier get() = CFModifier.padding(horizontal = this.dp)

// CFModifier.padding(vertical = this.dp)
inline val Int.pv: Modifier get() = CFModifier.padding(vertical = this.dp)

// this.padding(all = all.dp)
fun Modifier.p(all: Int): Modifier = this.padding(all = all.dp)

// this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)
fun Modifier.p(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Modifier = this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)

// this.padding(start = left.dp, top = top.dp, end = right.dp, bottom = bottom.dp)
fun Modifier.p(horizontal: Int, vertical: Int): Modifier = this.padding(horizontal = horizontal.dp, vertical = vertical.dp)

// this.padding(top = top.dp)
fun Modifier.pt(top: Int): Modifier = this.padding(top = top.dp)

// this.padding(bottom = bottom.dp)
fun Modifier.pb(bottom: Int): Modifier = this.padding(bottom = bottom.dp)

// this.padding(start = start.dp)
fun Modifier.ps(start: Int): Modifier = this.padding(start = start.dp)

// this.padding(end = end.dp)
fun Modifier.pe(end: Int): Modifier = this.padding(end = end.dp)

// this.padding(horizontal = horizontal.dp)
fun Modifier.ph(horizontal: Int): Modifier = this.padding(horizontal = horizontal.dp)

// this.padding(vertical = vertical.dp)
fun Modifier.pv(vertical: Int): Modifier = this.padding(vertical = vertical.dp)