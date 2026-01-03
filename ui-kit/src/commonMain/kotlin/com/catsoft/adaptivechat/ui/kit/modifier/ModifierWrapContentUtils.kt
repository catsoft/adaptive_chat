package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// CFModifier.wrapContentWidth()
inline val Modifier.wcW: Modifier @Composable get() = this.wrapContentWidth()


// CFModifier.wrapContentHeight()
inline val Modifier.wcH: Modifier @Composable get() = this.wrapContentHeight()


// CFModifier.wrapContentSize()
inline val Modifier.wcS: Modifier @Composable get() = this.wrapContentSize()


// CFModifier.wrapContentWidth()
inline val wcW: Modifier @Composable get() = Modifier.wrapContentWidth()


// CFModifier.wrapContentHeight()
inline val wcH: Modifier @Composable get() = Modifier.wrapContentHeight()


// CFModifier.wrapContentSize()
inline val wcS: Modifier @Composable get() = Modifier.wrapContentSize()

