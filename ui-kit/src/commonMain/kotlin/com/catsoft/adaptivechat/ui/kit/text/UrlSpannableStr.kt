package com.catsoft.adaptivechat.ui.kit.text

import com.catsoft.adaptivechat.ui.kit.api.textClause.LinkInfo

object UrlLinkExtractor {
    fun getLinkInfosWithUrlClean(text: String): List<LinkInfo> = getLinkInfos(cleanUrl(text))

    fun cleanUrl(url: String): String = url.replace("https://", "").replace("http://", "")

    fun getLinkInfos(text: String): List<LinkInfo> {
        val linkInfos = mutableListOf<LinkInfo>()
        // Web URL regex pattern - matches common URL formats
        val urlPattern = Regex(
            "(?:https?|ftp)://[^\\s/$.?#].[^\\s]*|" + // URLs with protocol
            "(?:www\\.)[^\\s/$.?#].[^\\s]*|" + // URLs starting with www.
            "[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?(?:/[^\\s]*)?" // Domain names
        )

        urlPattern.findAll(text).forEach { matchResult ->
            val url = matchResult.value
            val start = matchResult.range.first
            val end = matchResult.range.last + 1

            // Ensure URL has a protocol prefix for consistency
            val finalUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "https://$url"
            } else {
                url
            }

            linkInfos.add(LinkInfo(finalUrl, start, end))
        }

        return linkInfos
    }
}