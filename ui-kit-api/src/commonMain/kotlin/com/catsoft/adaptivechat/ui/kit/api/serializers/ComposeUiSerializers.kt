package com.catsoft.adaptivechat.ui.kit.api.serializers

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.serializer

object ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Color", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: Color) {
        encoder.encodeLong(value.value.toLong())
    }

    override fun deserialize(decoder: Decoder): Color {
        return Color(decoder.decodeLong().toULong())
    }
}

object TextUnitTypeSerializer : KSerializer<TextUnitType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TextUnitType", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: TextUnitType) {
        when (value) {
            TextUnitType.Sp -> encoder.encodeLong(UNIT_TYPE_SP)
            TextUnitType.Em -> encoder.encodeLong(UNIT_TYPE_EM)
            TextUnitType.Unspecified -> encoder.encodeLong(UNIT_TYPE_UNSPECIFIED)
        }
    }

    override fun deserialize(decoder: Decoder): TextUnitType {
        return when (decoder.decodeLong()) {
            UNIT_TYPE_SP -> TextUnitType.Sp
            UNIT_TYPE_EM -> TextUnitType.Em
            UNIT_TYPE_UNSPECIFIED -> TextUnitType.Unspecified
            else -> throw IllegalArgumentException("Unknown TextUnitType value")
        }
    }

    private const val UNIT_TYPE_UNSPECIFIED = 0x00L shl 32 // 0x00_0000_0000
    private const val UNIT_TYPE_SP = 0x01L shl 32 // 0x01_0000_0000
    private const val UNIT_TYPE_EM = 0x02L shl 32 // 0x2_0000_0000
}

object TextUnitSerializer : KSerializer<TextUnit> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TextUnit") {
        element<Float>("value", isOptional = false)
        element("type", TextUnitTypeSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: TextUnit) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, serializer<Float>(), value.value)
            encodeSerializableElement(descriptor, 1, TextUnitTypeSerializer, value.type)
        }
    }

    override fun deserialize(decoder: Decoder): TextUnit {
        return decoder.decodeStructure(descriptor) {
            val value = decodeFloatElement(descriptor, 0)
            val type = decodeSerializableElement(descriptor, 1, TextUnitTypeSerializer)

            when (type) {
                TextUnitType.Sp -> value.sp
                TextUnitType.Em -> value.em
                else -> throw IllegalArgumentException("Unknown TextUnitType: $type")
            }
        }
    }
}

object FontWeightSerializer : KSerializer<FontWeight> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("FontWeight", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: FontWeight) {
        encoder.encodeInt(value.weight)
    }

    override fun deserialize(decoder: Decoder): FontWeight {
        return FontWeight(decoder.decodeInt())
    }
}

object FontStyleSerializer : KSerializer<FontStyle> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("FontStyle", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: FontStyle) {
        encoder.encodeInt(value.value)
    }

    override fun deserialize(decoder: Decoder): FontStyle {
        return FontStyle(decoder.decodeInt())
    }
}

object TextDecorationSerializer : KSerializer<TextDecoration> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TextDecoration", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextDecoration) {
        encoder.encodeInt(value.mask)
    }

    override fun deserialize(decoder: Decoder): TextDecoration {
        return when (decoder.decodeInt()) {
            TextDecoration.None.mask -> TextDecoration.None
            TextDecoration.LineThrough.mask -> TextDecoration.LineThrough
            TextDecoration.Underline.mask -> TextDecoration.Underline
            else -> throw IllegalArgumentException("Unknown TextDecoration value")
        }
    }
}

object OffsetSerializer : KSerializer<Offset> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Offset") {
        element<Float>("x")
        element<Float>("y")
    }

    override fun serialize(encoder: Encoder, value: Offset) {
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.x)
            encodeFloatElement(descriptor, 1, value.y)
        }
    }

    override fun deserialize(decoder: Decoder): Offset {
        return decoder.decodeStructure(descriptor) {
            var x = 0f
            var y = 0f
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> x = decodeFloatElement(descriptor, 0)
                    1 -> y = decodeFloatElement(descriptor, 1)
                    else -> break
                }
            }
            Offset(x, y)
        }
    }
}

object ShadowSerializer : KSerializer<Shadow> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Shadow") {
        element("color", ColorSerializer.descriptor)
        element("offset", OffsetSerializer.descriptor)
        element<Float>("blurRadius")
    }

    override fun serialize(encoder: Encoder, value: Shadow) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, ColorSerializer, value.color)
            encodeSerializableElement(descriptor, 1, OffsetSerializer, value.offset)
            encodeFloatElement(descriptor, 2, value.blurRadius)
        }
    }

    override fun deserialize(decoder: Decoder): Shadow {
        return decoder.decodeStructure(descriptor) {
            var color: Color = Color.Unspecified
            var offset: Offset = Offset.Zero
            var blurRadius = 0f
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> color = decodeSerializableElement(descriptor, 0, ColorSerializer)
                    1 -> offset = decodeSerializableElement(descriptor, 1, OffsetSerializer)
                    2 -> blurRadius = decodeFloatElement(descriptor, 2)
                    else -> break
                }
            }
            Shadow(color, offset, blurRadius)
        }
    }
}

object BaselineShiftSerializer : KSerializer<BaselineShift> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BaselineShift", PrimitiveKind.FLOAT)

    override fun serialize(encoder: Encoder, value: BaselineShift) {
        encoder.encodeFloat(value.multiplier)
    }

    override fun deserialize(decoder: Decoder): BaselineShift {
        return BaselineShift(decoder.decodeFloat())
    }
}

object FontSynthesisSerializer : KSerializer<FontSynthesis> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("FontSynthesis", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: FontSynthesis) {
        when (value) {
            FontSynthesis.All -> encoder.encodeInt(AllFlags)
            FontSynthesis.Weight -> encoder.encodeInt(WeightFlag)
            FontSynthesis.Style -> encoder.encodeInt(StyleFlag)
            FontSynthesis.None -> encoder.encodeInt(0)
            else -> throw IllegalArgumentException("Unknown FontSynthesis value")
        }
    }

    override fun deserialize(decoder: Decoder): FontSynthesis {
        return when (decoder.decodeInt()) {
            AllFlags -> FontSynthesis.All
            WeightFlag -> FontSynthesis.Weight
            StyleFlag -> FontSynthesis.Style
            0 -> FontSynthesis.None
            else -> throw IllegalArgumentException("Unknown FontSynthesis value")
        }
    }

    private const val AllFlags = 0xffff
    private const val WeightFlag = 0x1
    private const val StyleFlag = 0x2
}

object TextGeometricTransformSerializer : KSerializer<TextGeometricTransform> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TextGeometricTransform") {
        element<Float>("scaleX")
        element<Float>("skewX")
    }

    override fun serialize(encoder: Encoder, value: TextGeometricTransform) {
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.scaleX)
            encodeFloatElement(descriptor, 1, value.skewX)
        }
    }

    override fun deserialize(decoder: Decoder): TextGeometricTransform {
        return decoder.decodeStructure(descriptor) {
            var scaleX = 1.0f
            var skewX = 0.0f
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> scaleX = decodeFloatElement(descriptor, 0)
                    1 -> skewX = decodeFloatElement(descriptor, 1)
                    else -> break
                }
            }
            TextGeometricTransform(scaleX, skewX)
        }
    }
}

object SpanStyleSerializer : KSerializer<SpanStyle> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SpanStyle") {
        element("color", ColorSerializer.descriptor)
        element("fontSize", TextUnitSerializer.descriptor)
        element("fontWeight", FontWeightSerializer.descriptor, isOptional = true)
        element("fontStyle", FontStyleSerializer.descriptor, isOptional = true)
        element("fontSynthesis", FontSynthesisSerializer.descriptor, isOptional = true)
        element<String?>("fontFeatureSettings", isOptional = true)
        element("letterSpacing", TextUnitSerializer.descriptor)
        element("baselineShift", BaselineShiftSerializer.descriptor, isOptional = true)
        element("textGeometricTransform", TextGeometricTransformSerializer.descriptor, isOptional = true)
        element("background", ColorSerializer.descriptor)
        element("textDecoration", TextDecorationSerializer.descriptor, isOptional = true)
        element("shadow", ShadowSerializer.descriptor, isOptional = true)
    }

    override fun serialize(encoder: Encoder, value: SpanStyle) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, ColorSerializer, value.color)
            encodeSerializableElement(descriptor, 1, TextUnitSerializer, value.fontSize)
            encodeNullableSerializableElement(descriptor, 2, FontWeightSerializer.nullable, value.fontWeight)
            encodeNullableSerializableElement(descriptor, 3, FontStyleSerializer.nullable, value.fontStyle)
            encodeNullableSerializableElement(descriptor, 4, FontSynthesisSerializer.nullable, value.fontSynthesis)
            encodeNullableSerializableElement(descriptor, 5, serializer<String>().nullable, value.fontFeatureSettings)
            encodeSerializableElement(descriptor, 6, TextUnitSerializer, value.letterSpacing)
            encodeNullableSerializableElement(descriptor, 7, BaselineShiftSerializer.nullable, value.baselineShift)
            encodeNullableSerializableElement(descriptor, 8, TextGeometricTransformSerializer.nullable, value.textGeometricTransform)
            encodeSerializableElement(descriptor, 9, ColorSerializer, value.background)
            encodeNullableSerializableElement(descriptor, 10, TextDecorationSerializer.nullable, value.textDecoration)
            encodeNullableSerializableElement(descriptor, 11, ShadowSerializer.nullable, value.shadow)
        }
    }

    override fun deserialize(decoder: Decoder): SpanStyle {
        return decoder.decodeStructure(descriptor) {
            var color: Color = Color.Unspecified
            var fontSize: TextUnit = TextUnit.Unspecified
            var fontWeight: FontWeight? = null
            var fontStyle: FontStyle? = null
            var fontSynthesis: FontSynthesis? = null
            var fontFeatureSettings: String? = null
            var letterSpacing: TextUnit = TextUnit.Unspecified
            var baselineShift: BaselineShift? = null
            var textGeometricTransform: TextGeometricTransform? = null
            var background: Color = Color.Unspecified
            var textDecoration: TextDecoration? = null
            var shadow: Shadow? = null

            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> color = decodeSerializableElement(descriptor, 0, ColorSerializer)
                    1 -> fontSize = decodeSerializableElement(descriptor, 1, TextUnitSerializer)
                    2 -> fontWeight = decodeNullableSerializableElement(descriptor, 2, FontWeightSerializer.nullable)
                    3 -> fontStyle = decodeNullableSerializableElement(descriptor, 3, FontStyleSerializer.nullable)
                    4 -> fontSynthesis = decodeNullableSerializableElement(descriptor, 4, FontSynthesisSerializer.nullable)
                    5 -> fontFeatureSettings = decodeNullableSerializableElement(descriptor, 5, serializer<String>().nullable)
                    6 -> letterSpacing = decodeSerializableElement(descriptor, 6, TextUnitSerializer)
                    7 -> baselineShift = decodeNullableSerializableElement(descriptor, 7, BaselineShiftSerializer.nullable)
                    8 -> textGeometricTransform = decodeNullableSerializableElement(descriptor, 8, TextGeometricTransformSerializer.nullable)
                    9 -> background = decodeSerializableElement(descriptor, 9, ColorSerializer)
                    10 -> textDecoration = decodeNullableSerializableElement(descriptor, 10, TextDecorationSerializer.nullable)
                    11 -> shadow = decodeNullableSerializableElement(descriptor, 11, ShadowSerializer.nullable)
                    -1 -> break@loop // End of structure
                    else -> error("Unexpected index: $index")
                }
            }
            SpanStyle(
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontStyle = fontStyle,
                fontSynthesis = fontSynthesis,
                fontFeatureSettings = fontFeatureSettings,
                letterSpacing = letterSpacing,
                baselineShift = baselineShift,
                textGeometricTransform = textGeometricTransform,
                background = background,
                textDecoration = textDecoration,
                shadow = shadow
            )
        }
    }
}

object TextAlignSerializer : KSerializer<TextAlign> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TextAlign", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextAlign) {
        return when (value) {
            TextAlign.Start -> encoder.encodeInt(start)
            TextAlign.End -> encoder.encodeInt(end)
            TextAlign.Left -> encoder.encodeInt(left)
            TextAlign.Right -> encoder.encodeInt(right)
            TextAlign.Center -> encoder.encodeInt(center)
            TextAlign.Justify -> encoder.encodeInt(justify)
            else -> throw IllegalArgumentException("Unknown TextAlign value: $value")
        }
    }

    override fun deserialize(decoder: Decoder): TextAlign {
        return when (val value = decoder.decodeInt()) {
            start -> TextAlign.Start
            end -> TextAlign.End
            left -> TextAlign.Left
            right -> TextAlign.Right
            center -> TextAlign.Center
            justify -> TextAlign.Justify
            else -> throw IllegalArgumentException("Unknown TextAlign value: $value")
        }
    }

    private const val left = 1
    private const val right = 2
    private const val center = 3
    private const val justify = 4
    private const val start = 5
    private const val end = 6
}

object TextIndentSerializer : KSerializer<TextIndent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TextIndent") {
        element("firstLine", TextUnitSerializer.descriptor)
        element("restLine", TextUnitSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: TextIndent) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, TextUnitSerializer, value.firstLine)
            encodeSerializableElement(descriptor, 1, TextUnitSerializer, value.restLine)
        }
    }

    override fun deserialize(decoder: Decoder): TextIndent {
        return decoder.decodeStructure(descriptor) {
            var firstLine: TextUnit = TextUnit.Unspecified
            var restLine: TextUnit = TextUnit.Unspecified
            // Read in order, or handle out-of-order if format supports it (decodeElementIndex)
            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> firstLine = decodeSerializableElement(descriptor, 0, TextUnitSerializer)
                    1 -> restLine = decodeSerializableElement(descriptor, 1, TextUnitSerializer)
                    -1 -> break@loop // End of structure
                    else -> error("Unexpected index: $index")
                }
            }
            TextIndent(firstLine = firstLine, restLine = restLine)
        }
    }
}

object TextDirectionSerializer : KSerializer<TextDirection> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TextDirection", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextDirection) {
        when (value) {
            TextDirection.Ltr -> encoder.encodeInt(ltyValue)
            TextDirection.Rtl -> encoder.encodeInt(rtlValue)
            TextDirection.Content -> encoder.encodeInt(contentValue)
            TextDirection.ContentOrLtr -> encoder.encodeInt(contentOrLtrValue)
            TextDirection.ContentOrRtl -> encoder.encodeInt(contentOrRtlValue)
        }
    }

    override fun deserialize(decoder: Decoder): TextDirection {
        return when (decoder.decodeInt()) {
            ltyValue -> TextDirection.Ltr
            rtlValue -> TextDirection.Rtl
            contentValue -> TextDirection.Content
            contentOrLtrValue -> TextDirection.ContentOrLtr
            contentOrRtlValue -> TextDirection.ContentOrRtl
            else -> throw IllegalArgumentException("Unknown TextDirection value")
        }
    }

    val ltyValue = 1
    val rtlValue = 2
    val contentValue = 3
    val contentOrLtrValue = 4
    val contentOrRtlValue = 5
}

object TextOverflowSerializer : KSerializer<TextOverflow> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TextOverflow", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextOverflow) {
        when (value) {
            TextOverflow.Clip -> encoder.encodeInt(clipValue)
            TextOverflow.Ellipsis -> encoder.encodeInt(ellipsisValue)
            TextOverflow.Visible -> encoder.encodeInt(visibleValue)
            TextOverflow.StartEllipsis -> encoder.encodeInt(startEllipsisValue)
            TextOverflow.MiddleEllipsis -> encoder.encodeInt(middleEllipsisValue)
        }
    }

    override fun deserialize(decoder: Decoder): TextOverflow {
        return when (decoder.decodeInt()) {
            clipValue -> TextOverflow.Clip
            ellipsisValue -> TextOverflow.Ellipsis
            visibleValue -> TextOverflow.Visible
            startEllipsisValue -> TextOverflow.StartEllipsis
            middleEllipsisValue -> TextOverflow.MiddleEllipsis
            else -> throw IllegalArgumentException("Unknown TextOverflow value")
        }
    }

    val clipValue = 1
    val ellipsisValue = 2
    val visibleValue = 3
    val startEllipsisValue = 4
    val middleEllipsisValue = 5
}



object AnnotatedStringSerializer : KSerializer<AnnotatedString> {

    @Serializable
    private data class SerializableSpanStyleRange(
        @Serializable(with = SpanStyleSerializer::class) val style: SpanStyle,
        val start: Int,
        val end: Int
    )

    private val spanStyleRangeListSerializer = ListSerializer(SerializableSpanStyleRange.serializer())

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("AnnotatedString") {
        element<String>("text")
        element("spanStyles", spanStyleRangeListSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: AnnotatedString) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.text)

            val serializableSpanStyles = value.spanStyles.map { range ->
                SerializableSpanStyleRange(style = range.item, start = range.start, end = range.end)
            }
            encodeSerializableElement(descriptor, 1, spanStyleRangeListSerializer, serializableSpanStyles)
        }
    }

    override fun deserialize(decoder: Decoder): AnnotatedString {
        return decoder.decodeStructure(descriptor) {
            var text: String? = null
            var deserializedSpanStyles: List<SerializableSpanStyleRange>? = null // Renamed for clarity

            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> text = decodeStringElement(descriptor, 0)
                    1 -> deserializedSpanStyles = decodeSerializableElement(descriptor, 1, spanStyleRangeListSerializer)
                    -1 -> break@loop
                    else -> error("Unexpected index: $index")
                }
            }

            requireNotNull(text) { "Deserialized text for AnnotatedString cannot be null" }

            val builder = AnnotatedString.Builder() // Can construct with capacity if text.length is known
            builder.append(text) // Append text first

            deserializedSpanStyles?.forEach { range ->
                builder.addStyle(style = range.style, start = range.start, end = range.end)
            }
            builder.toAnnotatedString()
        }
    }
}