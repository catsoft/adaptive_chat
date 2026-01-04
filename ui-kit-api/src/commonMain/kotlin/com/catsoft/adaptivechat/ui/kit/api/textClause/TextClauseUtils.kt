package com.catsoft.adaptivechat.ui.kit.api.textClause

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun StringResource.s(vararg args: String): String {
    val res = stringResource(this, *args)
    return res
}

fun StringResource.str(vararg args: Any): TextClause.Resource = TextClause.Resource(this, args.toList().map { it.toString() })

fun StringResource.strStyled(style: TextStyle, vararg args: Any): TextClause.Resource = TextClause.Resource(this, args.toList().map { it.toString() }, style = style)

fun String?.raw(): TextClause = if (this == null) TextClause.Empty else TextClause.Raw(this)
fun String?.nullOrBlankRaw(): TextClause? = if (this == null || this.isBlank()) null else TextClause.Raw(this)

fun StringResource.annotated(annotation: List<TextAnnotation>, vararg args: Any) = TextClause.Annotated(this, args.toList().map { it.toString() }, annotation)

fun String.annotated(annotation: List<TextAnnotation>, vararg args: Any) = TextClause.AnnotatedRaw(this, args.toList(), annotation)

fun AnnotatedString.annotated() = TextClause.AnnotatedString(this)