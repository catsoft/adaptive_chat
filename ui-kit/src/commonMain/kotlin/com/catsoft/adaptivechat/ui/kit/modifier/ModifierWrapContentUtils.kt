package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Modifier.wrapContentWidth()
inline val Modifier.wcW: Modifier @Composable get() = this.wrapContentWidth()


// Modifier.wrapContentHeight()
inline val Modifier.wcH: Modifier @Composable get() = this.wrapContentHeight()


// Modifier.wrapContentSize()
inline val Modifier.wcS: Modifier @Composable get() = this.wrapContentSize()


// Modifier.wrapContentWidth()
inline val wcW: Modifier @Composable get() = Modifier.wrapContentWidth()


// Modifier.wrapContentHeight()
inline val wcH: Modifier @Composable get() = Modifier.wrapContentHeight()


// Modifier.wrapContentSize()
inline val wcS: Modifier @Composable get() = Modifier.wrapContentSize()

