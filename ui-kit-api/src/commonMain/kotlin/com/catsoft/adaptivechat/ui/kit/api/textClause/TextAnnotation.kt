package com.catsoft.adaptivechat.ui.kit.api.textClause

import androidx.compose.ui.text.SpanStyle
import com.catsoft.adaptivechat.ui.kit.api.serializers.SpanStyleSerializer
import kotlinx.serialization.Serializable

@Serializable
data class TextAnnotation(
    val textToStyle: String,
    @Serializable(with = SpanStyleSerializer::class)
    val style: SpanStyle,
    val caseSensitive: Boolean = true,
    val occurrence: Int = -1
)
