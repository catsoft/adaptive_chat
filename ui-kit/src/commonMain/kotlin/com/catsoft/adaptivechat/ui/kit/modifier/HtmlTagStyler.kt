package com.catsoft.adaptivechat.ui.kit.modifier

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

private enum class HtmlTag(val tagName: String, val styleFactory: () -> SpanStyle) {
    BOLD("b", { SpanStyle(fontWeight = FontWeight.Bold) }),
    ITALIC("i", { SpanStyle(fontStyle = FontStyle.Italic) }),
    UNDERLINE("u", { SpanStyle(textDecoration = TextDecoration.Underline) });

    companion object {
        fun fromName(name: String): HtmlTag? = entries.firstOrNull { it.tagName.equals(name, ignoreCase = true) }
    }
}

private data class TagInfo(val tag: HtmlTag, val startIndex: Int)

private fun String.decodeHtmlEntities(): String {
    return this
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")
}

fun parseStyledText(source: String): AnnotatedString {
    val decoded = source.decodeHtmlEntities()
    
    if (!decoded.contains('<')) {
        return AnnotatedString(source)
    }

    val output = StringBuilder()
    val tagStack = mutableListOf<TagInfo>()
    val spans = mutableListOf<AnnotatedString.Range<SpanStyle>>()
    
    var i = 0
    while (i < decoded.length) {
        if (decoded[i] == '<') {
            val tagEnd = decoded.indexOf('>', i)
            if (tagEnd == -1) {
                output.append(decoded[i])
                i++
                continue
            }
            
            val tagContent = decoded.substring(i + 1, tagEnd).trim()
            val isClosing = tagContent.startsWith('/')
            val tagName = if (isClosing) {
                tagContent.substring(1).substringBefore(' ').trim()
            } else {
                tagContent.substringBefore(' ').trim()
            }
            
            val recognizedTag = HtmlTag.fromName(tagName)
            
            if (recognizedTag != null) {
                if (isClosing) {
                    val openTagIndex = tagStack.indexOfLast { it.tag == recognizedTag }
                    if (openTagIndex != -1) {
                        val tagInfo = tagStack.removeAt(openTagIndex)
                        val endIndex = output.length
                        if (endIndex > tagInfo.startIndex) {
                            spans.add(
                                AnnotatedString.Range(
                                    item = tagInfo.tag.styleFactory(),
                                    start = tagInfo.startIndex,
                                    end = endIndex
                                )
                            )
                        }
                    }
                } else {
                    tagStack.add(TagInfo(recognizedTag, output.length))
                }
                i = tagEnd + 1
            } else {
                output.append(decoded[i])
                i++
            }
        } else {
            output.append(decoded[i])
            i++
        }
    }
    
    while (tagStack.isNotEmpty()) {
        val tagInfo = tagStack.removeLast()
        val endIndex = output.length
        if (endIndex > tagInfo.startIndex) {
            spans.add(
                AnnotatedString.Range(
                    item = tagInfo.tag.styleFactory(),
                    start = tagInfo.startIndex,
                    end = endIndex
                )
            )
        }
    }
    
    return buildAnnotatedString {
        append(output.toString())
        spans.forEach { span ->
            addStyle(span.item, span.start, span.end)
        }
    }
}

fun parseStyledTextOrPlain(text: String): AnnotatedString {
    return if (text.contains('<')) {
        parseStyledText(text)
    } else {
        AnnotatedString(text)
    }
}

fun mergeSpanStyles(
    base: AnnotatedString,
    extra: List<AnnotatedString.Range<SpanStyle>>
): AnnotatedString {
    return buildAnnotatedString {
        append(base.text)
        
        base.spanStyles.forEach { span ->
            addStyle(span.item, span.start, span.end)
        }
        
        extra.forEach { span ->
            addStyle(span.item, span.start, span.end)
        }
        
        base.paragraphStyles.forEach { paragraph ->
            addStyle(paragraph.item, paragraph.start, paragraph.end)
        }
    }
}
