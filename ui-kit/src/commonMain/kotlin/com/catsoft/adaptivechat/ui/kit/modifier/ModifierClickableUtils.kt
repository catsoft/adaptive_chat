package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

/**
 * Shortcut for [Modifier.clickable] with onClick action
 * @see androidx.compose.foundation.clickable
 */
fun Modifier.c(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    onClick: () -> Unit
): Modifier = this.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    onClick = onClick
)

/**
 * Shortcut for [Modifier.clickable] without ripple effect
 * @see androidx.compose.foundation.clickable
 */
fun Modifier.cNR(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    onClick: () -> Unit
): Modifier = this.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    indication = null,
    interactionSource = MutableInteractionSource(),
    onClick = onClick
)
