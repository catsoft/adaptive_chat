package com.catsoft.adaptivechat.ui.kit.api.textClause

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.catsoft.adaptivechat.localization.StringsManager
import com.catsoft.adaptivechat.ui.kit.api.serializers.AnnotatedStringSerializer
import com.catsoft.adaptivechat.ui.kit.api.serializers.StringResourceKeySerializer
import com.catsoft.adaptivechat.ui.kit.api.serializers.TextOverflowSerializer
import com.catsoft.adaptivechat.ui.kit.api.serializers.TextStyleSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Serializable
sealed class TextClause {

    abstract val modifier: Modifier
    abstract var style: TextStyle?

    abstract var overflow: TextOverflow

    abstract var maxLines: Int

    @Serializable
    data object Empty : TextClause() {
        override var style: TextStyle? = TextStyle.Default
        override val modifier: Modifier = Modifier
        override var overflow: TextOverflow = TextOverflow.Visible
        override var maxLines: Int = Int.MAX_VALUE

        override fun isEmpty(): Boolean = true
        override fun isNotEmpty(): Boolean = false

        @Composable
        override fun getText(): String = ""

        override suspend fun getText(stringsManager: StringsManager): String = ""
    }

    @Serializable
    data class Resource(
        @Serializable(with = StringResourceKeySerializer::class)
        val resId: StringResource,
        val args: List<String> = emptyList(),
        override val modifier: Modifier = Modifier,
        @Serializable(with = TextStyleSerializer::class)
        override var style: TextStyle? = null,
        @Serializable(with = TextOverflowSerializer::class)
        override var overflow: TextOverflow = TextOverflow.Visible,
        override var maxLines: Int = Int.MAX_VALUE
    ) : TextClause() {
        override fun isEmpty(): Boolean = false
        override fun isNotEmpty(): Boolean = true

        @Composable
        override fun getText() = stringResource(resId, *args.toTypedArray())

        override suspend fun getText(stringsManager: StringsManager): String = stringsManager.getString(resId, args)
    }

    @Serializable
    data class Raw(
        val text: String,
        override val modifier: Modifier = Modifier,
        @Serializable(with = TextStyleSerializer::class)
        override var style: TextStyle? = null,
        @Serializable(with = TextOverflowSerializer::class)
        override var overflow: TextOverflow = TextOverflow.Visible,
        override var maxLines: Int = Int.MAX_VALUE
    ) : TextClause() {
        override fun isEmpty(): Boolean = text.isEmpty()
        override fun isNotEmpty(): Boolean = text.isNotEmpty()

        @Composable
        override fun getText() = text

        override suspend fun getText(stringsManager: StringsManager): String = text
    }

    @Serializable
    data class Annotated(
        @Serializable(with = StringResourceKeySerializer::class)
        val resId: StringResource,
        val args: List<String> = emptyList(),
        val annotations: List<TextAnnotation> = emptyList(),
        override val modifier: Modifier = Modifier,
        @Serializable(with = TextStyleSerializer::class)
        override var style: TextStyle? = null,
        @Serializable(with = TextOverflowSerializer::class)
        override var overflow: TextOverflow = TextOverflow.Visible,
        override var maxLines: Int = Int.MAX_VALUE
    ) : TextClause() {
        override fun isEmpty(): Boolean = false
        override fun isNotEmpty(): Boolean = true

        @Composable
        override fun getText() = stringResource(resId, *args.toTypedArray())

        override suspend fun getText(stringsManager: StringsManager): String = stringsManager.getString(resId, args)
    }

    data class AnnotatedRaw(
        val text: String,
        val args: List<Any> = emptyList(),
        val annotations: List<TextAnnotation> = emptyList(),
        override val modifier: Modifier = Modifier,
        override var style: TextStyle? = null,
        override var overflow: TextOverflow = TextOverflow.Visible,
        override var maxLines: Int = Int.MAX_VALUE
    ) : TextClause() {
        override fun isEmpty(): Boolean = text.isEmpty()
        override fun isNotEmpty(): Boolean = text.isNotEmpty()

        @Composable
        override fun getText() = text

        override suspend fun getText(stringsManager: StringsManager): String = text
    }

    @Serializable
    data class AnnotatedString(
        @Serializable(with = AnnotatedStringSerializer::class)
        val text: androidx.compose.ui.text.AnnotatedString,
        override val modifier: Modifier = Modifier,
        @Serializable(with = TextStyleSerializer::class)
        override var style: TextStyle? = null,
        @Serializable(with = TextOverflowSerializer::class)
        override var overflow: TextOverflow = TextOverflow.Visible,
        override var maxLines: Int = Int.MAX_VALUE
    ) : TextClause() {
        override fun isEmpty(): Boolean = text.isEmpty()
        override fun isNotEmpty(): Boolean = text.isNotEmpty()

        @Composable
        override fun getText() = text.text

        override suspend fun getText(stringsManager: StringsManager): String = text.text
    }

    abstract fun isEmpty(): Boolean

    abstract fun isNotEmpty(): Boolean

    @Composable
    abstract fun getText(): String

    abstract suspend fun getText(stringsManager: StringsManager): String
}

fun TextClause?.isEmpty(): Boolean = this?.isEmpty() ?: true

fun TextClause?.isNotEmpty(): Boolean = this?.isNotEmpty() ?: false

fun TextClause.withStyle(style: TextStyle?): TextClause {
    this.style = style
    return this
}
