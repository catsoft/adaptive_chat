package com.catsoft.adaptivechat.ui.kit.text

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.catsoft.adaptivechat.logger.logger
import com.catsoft.adaptivechat.ui.kit.api.textClause.TextAnnotation
import com.catsoft.adaptivechat.ui.kit.api.textClause.TextClause
import com.catsoft.adaptivechat.ui.kit.api.textClause.s
import com.catsoft.adaptivechat.ui.kit.modifier.m
import com.catsoft.adaptivechat.ui.kit.modifier.parseStyledText

@Composable
fun CFText(
    text: TextClause,
    style: TextStyle = TextStyle.Default,
    linkStyle: SpanStyle? = null,
    modifier: Modifier = m,
    overflow: TextOverflow? = null,
    maxLines: Int? = null,
) {
    val localModifier = modifier.then(text.modifier)
    val localStyle = style.merge(text.style)
    val overflow = overflow ?: text.overflow
    val maxLines = maxLines ?: text.maxLines


    when (text) {
        is TextClause.Empty -> Unit

        is TextClause.Resource -> {
            val rawText = text.resId.s(*text.args.toTypedArray())
            val parsedText = remember(rawText) { parseStyledText(rawText) }
            AnnotatedText(parsedText, emptyList(), localModifier, localStyle, linkStyle, overflow, maxLines)
        }

        is TextClause.Raw -> {
            val parsedText = remember(text.text) { parseStyledText(text.text) }
            AnnotatedText(parsedText, emptyList(), localModifier, localStyle, linkStyle, overflow, maxLines)
        }

        is TextClause.AnnotatedString -> {
            AnnotatedText(text.text, emptyList(), localModifier, localStyle, linkStyle, overflow, maxLines)
        }

        is TextClause.Annotated -> {
            val rawText = text.resId.s(*text.args.toTypedArray())
            val parsedText = remember(rawText) { parseStyledText(rawText) }
            AnnotatedText(parsedText, text.annotations, localModifier, localStyle, linkStyle, overflow, maxLines)
        }

        is TextClause.AnnotatedRaw -> {
            val parsedText = remember(text.text) { parseStyledText(text.text) }
            AnnotatedText(parsedText, text.annotations, localModifier, localStyle, linkStyle, overflow, maxLines)
        }
    }
}

@Composable
fun AnnotatedText(
    text: AnnotatedString,
    annotation: List<TextAnnotation>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    linkStyle: SpanStyle? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    logger().e(text.text)

    val finalText = remember(text, annotation) {
        if (annotation.isEmpty()) {
            text
        } else {
            val additionalSpans = mutableListOf<AnnotatedString.Range<SpanStyle>>()

            annotation.forEach { ann ->
                val searchText = ann.textToStyle
                val sourceText = text.text

                var count = 0
                var startIndex = 0

                while (true) {
                    val index = sourceText.indexOf(searchText, startIndex, ignoreCase = !ann.caseSensitive)

                    if (index == -1 || (ann.occurrence != -1 && count >= ann.occurrence)) break

                    val endIndex = index + searchText.length
                    additionalSpans.add(
                        AnnotatedString.Range(
                            item = ann.style,
                            start = index,
                            end = endIndex
                        )
                    )

                    startIndex = endIndex
                    count++
                }
            }

            buildAnnotatedString {
                append(text.text)
                text.spanStyles.forEach { span ->
                    addStyle(span.item, span.start, span.end)
                }
                additionalSpans.forEach { span ->
                    addStyle(span.item, span.start, span.end)
                }
            }
        }
    }

    logger().e(finalText.text)

    val hasLinkAnnotations = remember(finalText) {
        finalText.getLinkAnnotations(0, finalText.length).isNotEmpty()
    }

    if (hasLinkAnnotations) {
        ClickableTextWithLinks(
            text = finalText,
            modifier = modifier,
            style = textStyle,
            overflow = overflow,
            maxLines = maxLines,
            onClickLink = { url ->
                // todo
            }
        )
    } else {
        val span = linkStyle ?: SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
        LinkifyText(
            text = finalText,
            modifier = modifier,
            style = textStyle,
            overflow = overflow,
            linkStyle = span,
            maxLines = maxLines,
            linking = linkStyle != null,
            clickable = linkStyle != null
        ) {
            // todo
        }
    }
}

@Composable
private fun ClickableTextWithLinks(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onClickLink: (String) -> Unit
) {
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = text,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { pos ->
                layoutResult?.let { layout ->
                    val offset = layout.getOffsetForPosition(pos)

                    val linkAnnotations = text.getLinkAnnotations(offset, offset)
                    logger().e("Found ${linkAnnotations.size} link annotations at offset $offset")

                    if (linkAnnotations.isNotEmpty()) {
                        when (val link = linkAnnotations.first().item) {
                            is LinkAnnotation.Clickable -> {
                                logger().e("Invoking Clickable link")
                                link.linkInteractionListener?.onClick(link)
                            }
                            is LinkAnnotation.Url -> {
                                logger().e("Opening URL: ${link.url}")
                                onClickLink(link.url)
                            }
                        }
                    }
                }
            }
        },
        style = style,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = { layoutResult = it }
    )
}