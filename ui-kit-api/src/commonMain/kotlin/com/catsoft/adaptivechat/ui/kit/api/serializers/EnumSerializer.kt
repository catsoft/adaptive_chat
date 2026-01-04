package com.catsoft.adaptivechat.ui.kit.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

inline fun <reified T : Enum<T>> enumSerializer(): BaseEnumSerializer<T> =
    BaseEnumSerializer({ enumValueOf<T>(it) }, { it.name })

open class BaseEnumSerializer<T>(
    private val fromString: (String) -> T,
    private val toString: (T) -> String,
) : KSerializer<T> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("AudioTranscriptionStatus", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(toString(value))
    }

    override fun deserialize(decoder: Decoder): T {
        return fromString(decoder.decodeString())
    }
}