package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chatfuel.shared.uiKit.modifier.CFModifier

// CFModifier.align(Alignment.TopStart)
inline val BoxScope.alignTopStart: Modifier get() = CFModifier.align(Alignment.TopStart)

// CFModifier.align(Alignment.TopCenter)
inline val BoxScope.alignTopCenter: Modifier get() = CFModifier.align(Alignment.TopCenter)

// CFModifier.align(Alignment.TopEnd)
inline val BoxScope.alignTopEnd: Modifier get() = CFModifier.align(Alignment.TopEnd)

// CFModifier.align(Alignment.CenterStart)
inline val BoxScope.alignCenterStart: Modifier get() = CFModifier.align(Alignment.CenterStart)

// CFModifier.align(Alignment.Center)
inline val BoxScope.alignCenter: Modifier get() = CFModifier.align(Alignment.Center)

// CFModifier.align(Alignment.CenterEnd)
inline val BoxScope.alignCenterEnd: Modifier get() = CFModifier.align(Alignment.CenterEnd)

// CFModifier.align(Alignment.BottomStart)
inline val BoxScope.alignBottomStart: Modifier get() = CFModifier.align(Alignment.BottomStart)

// CFModifier.align(Alignment.BottomCenter)
inline val BoxScope.alignBottomCenter: Modifier get() = CFModifier.align(Alignment.BottomCenter)

// CFModifier.align(Alignment.BottomEnd)
inline val BoxScope.alignBottomEnd: Modifier get() = CFModifier.align(Alignment.BottomEnd)