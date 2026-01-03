package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier

/**
 * Shortcut for [Modifier.wrapContentWidth]
 * @see androidx.compose.foundation.layout.wrapContentWidth
 */
inline val Modifier.wcW: Modifier get() = this.wrapContentWidth()

/**
 * Shortcut for [Modifier.wrapContentHeight]
 * @see androidx.compose.foundation.layout.wrapContentHeight
 */
inline val Modifier.wcH: Modifier get() = this.wrapContentHeight()

/**
 * Shortcut for [Modifier.wrapContentSize]
 * @see androidx.compose.foundation.layout.wrapContentSize
 */
inline val Modifier.wcS: Modifier get() = this.wrapContentSize()

/**
 * Shortcut for [Modifier.wrapContentWidth]
 * @see androidx.compose.foundation.layout.wrapContentWidth
 */
inline val wcW: Modifier get() = Modifier.wrapContentWidth()

/**
 * Shortcut for [Modifier.wrapContentHeight]
 * @see androidx.compose.foundation.layout.wrapContentHeight
 */
inline val wcH: Modifier get() = Modifier.wrapContentHeight()

/**
 * Shortcut for [Modifier.wrapContentSize]
 * @see androidx.compose.foundation.layout.wrapContentSize
 */
inline val wcS: Modifier get() = Modifier.wrapContentSize()

