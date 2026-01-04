package com.catsoft.adaptivechat.ui.kit.api.textClause

/**
 * Represents information about a URL link found in text.
 */
data class LinkInfo(
    val url: String,
    val start: Int,
    val end: Int
)
