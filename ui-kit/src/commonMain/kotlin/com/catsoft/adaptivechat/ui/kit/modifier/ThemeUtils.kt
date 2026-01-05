package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.style.TextAlign

/**
 * Shortcut for MaterialTheme.typography
 * @see MaterialTheme.typography
 */
inline val typo: Typography
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.typography

/**
 * Shortcut for MaterialTheme.colorScheme
 * @see MaterialTheme.colorScheme
 */
inline val colors: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme
