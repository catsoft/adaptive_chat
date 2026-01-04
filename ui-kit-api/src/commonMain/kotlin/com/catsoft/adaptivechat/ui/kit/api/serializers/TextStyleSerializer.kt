package com.catsoft.adaptivechat.ui.kit.api.serializers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.serializer

object TextStyleSerializer : KSerializer<TextStyle> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TextStyle") {
        element("color", ColorSerializer.descriptor, isOptional = true)
        element("fontSize", TextUnitSerializer.descriptor, isOptional = true)
        element("fontWeight", FontWeightSerializer.descriptor, isOptional = true)
        element("fontStyle", FontStyleSerializer.descriptor, isOptional = true)
        element("fontSynthesis", FontSynthesisSerializer.descriptor, isOptional = true)
        element<String?>("fontFamily", isOptional = true) // Representing FontFamily by its toString()
        element<String?>("fontFeatureSettings", isOptional = true)
        element("letterSpacing", TextUnitSerializer.descriptor, isOptional = true)
        element("baselineShift", BaselineShiftSerializer.descriptor, isOptional = true)
        element("textGeometricTransform", TextGeometricTransformSerializer.descriptor, isOptional = true)
        element<String?>("localeList", isOptional = true) // Representing LocaleList by string tags
        element("background", ColorSerializer.descriptor, isOptional = true)
        element("textDecoration", TextDecorationSerializer.descriptor, isOptional = true)
        element("shadow", ShadowSerializer.descriptor, isOptional = true)
        element("textAlign", TextAlignSerializer.descriptor, isOptional = true)
        element("textDirection", TextDirectionSerializer.descriptor, isOptional = true)
        element("lineHeight", TextUnitSerializer.descriptor, isOptional = true)
        element("textIndent", TextIndentSerializer.descriptor, isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: TextStyle) {
        encoder.encodeStructure(descriptor) {
            if (value.color != Color.Unspecified) encodeSerializableElement(descriptor, 0,
                ColorSerializer, value.color)
            if (value.fontSize != TextUnit.Unspecified) encodeSerializableElement(descriptor, 1,
                TextUnitSerializer, value.fontSize)
            value.fontWeight?.let { encodeSerializableElement(descriptor, 2,
                FontWeightSerializer, it) }
            value.fontStyle?.let { encodeSerializableElement(descriptor, 3, FontStyleSerializer, it) }
            value.fontSynthesis?.let { encodeSerializableElement(descriptor, 4,
                FontSynthesisSerializer, it) }
            value.fontFamily?.let { ff ->
                encodeNullableSerializableElement(descriptor, 5, serializer<String>().nullable, ff.toString()) // Changed to ff.toString()
            }
            value.fontFeatureSettings?.let { encodeNullableSerializableElement(descriptor, 6, serializer<String>().nullable, it) }
            if (value.letterSpacing != TextUnit.Unspecified) encodeSerializableElement(descriptor, 7,
                TextUnitSerializer, value.letterSpacing)
            value.baselineShift?.let { encodeSerializableElement(descriptor, 8,
                BaselineShiftSerializer, it) }
            value.textGeometricTransform?.let { encodeSerializableElement(descriptor, 9,
                TextGeometricTransformSerializer, it) }
            value.localeList?.let { localeList ->
                encodeNullableSerializableElement(descriptor, 10, serializer<String>().nullable, localeList.toLanguageTagsString())
            }
            if (value.background != Color.Unspecified) encodeSerializableElement(descriptor, 11,
                ColorSerializer, value.background)
            value.textDecoration?.let { encodeSerializableElement(descriptor, 12,
                TextDecorationSerializer, it) }
            value.shadow?.let { encodeSerializableElement(descriptor, 13, ShadowSerializer, it) }
            value.textAlign?.let { encodeSerializableElement(descriptor, 14, TextAlignSerializer, it) }
            value.textDirection?.let { encodeSerializableElement(descriptor, 15,
                TextDirectionSerializer, it) }
            if (value.lineHeight != TextUnit.Unspecified) encodeSerializableElement(descriptor, 16,
                TextUnitSerializer, value.lineHeight)
            value.textIndent?.let { encodeSerializableElement(descriptor, 17,
                TextIndentSerializer, it) }
        }
    }

    override fun deserialize(decoder: Decoder): TextStyle {
        var color: Color = Color.Unspecified
        var fontSize: TextUnit = TextUnit.Unspecified
        var fontWeight: FontWeight? = null
        var fontStyle: FontStyle? = null
        var fontSynthesis: FontSynthesis? = null
        var fontFamilyString: String? = null
        var fontFeatureSettings: String? = null
        var letterSpacing: TextUnit = TextUnit.Unspecified
        var baselineShift: BaselineShift? = null
        var textGeometricTransform: TextGeometricTransform? = null
        var localeListString: String? = null
        var background: Color = Color.Unspecified
        var textDecoration: TextDecoration? = null
        var shadow: Shadow? = null
        var textAlign: TextAlign? = null
        var textDirection: TextDirection? = null
        var lineHeight: TextUnit = TextUnit.Unspecified
        var textIndent: TextIndent? = null

        decoder.decodeStructure(descriptor) {
            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> color = decodeSerializableElement(descriptor, 0, ColorSerializer)
                    1 -> fontSize = decodeSerializableElement(descriptor, 1, TextUnitSerializer)
                    2 -> fontWeight = decodeSerializableElement(descriptor, 2, FontWeightSerializer)
                    3 -> fontStyle = decodeSerializableElement(descriptor, 3, FontStyleSerializer)
                    4 -> fontSynthesis = decodeSerializableElement(descriptor, 4,
                        FontSynthesisSerializer
                    )
                    5 -> fontFamilyString = decodeNullableSerializableElement(descriptor, 5, serializer<String>().nullable)
                    6 -> fontFeatureSettings = decodeNullableSerializableElement(descriptor, 6, serializer<String>().nullable)
                    7 -> letterSpacing = decodeSerializableElement(descriptor, 7,
                        TextUnitSerializer
                    )
                    8 -> baselineShift = decodeSerializableElement(descriptor, 8,
                        BaselineShiftSerializer
                    )
                    9 -> textGeometricTransform = decodeSerializableElement(descriptor, 9,
                        TextGeometricTransformSerializer
                    )
                    10 -> localeListString = decodeNullableSerializableElement(descriptor, 10, serializer<String>().nullable)
                    11 -> background = decodeSerializableElement(descriptor, 11, ColorSerializer)
                    12 -> textDecoration = decodeSerializableElement(descriptor, 12,
                        TextDecorationSerializer
                    )
                    13 -> shadow = decodeSerializableElement(descriptor, 13, ShadowSerializer)
                    14 -> textAlign = decodeSerializableElement(descriptor, 14, TextAlignSerializer)
                    15 -> textDirection = decodeSerializableElement(descriptor, 15,
                        TextDirectionSerializer
                    )
                    16 -> lineHeight = decodeSerializableElement(descriptor, 16, TextUnitSerializer)
                    17 -> textIndent = decodeSerializableElement(descriptor, 17,
                        TextIndentSerializer
                    )
                    -1 -> break@loop
                    else -> error("Unexpected index: $index")
                }
            }
        }

        val deserializedFontFamily = fontFamilyString?.let {
            when(it) {
                "FontFamily.SansSerif" -> FontFamily.SansSerif
                "FontFamily.Serif" -> FontFamily.Serif
                "FontFamily.Monospace" -> FontFamily.Monospace
                "FontFamily.Cursive" -> FontFamily.Cursive
                else -> FontFamily.Default
            }
        }

        val deserializedLocaleList = localeListString?.let { LocaleList(it) }

        return TextStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFamily = deserializedFontFamily, // Use the deserialized/placeholder value
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = deserializedLocaleList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            textAlign = textAlign!!,
            textDirection = textDirection!!,
            lineHeight = lineHeight,
            textIndent = textIndent
        )
    }
}

// Helper extension function for LocaleList
internal fun LocaleList.toLanguageTagsString(): String? {
    if (this.isEmpty()) {
        return null // Or return "" if you prefer to always have a string
    }
    return this.joinToString(",") { it.toLanguageTag() }
}