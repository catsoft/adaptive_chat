package com.catsoft.adaptivechat.ui.kit.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.compose.resources.StringResource

object StringResourceKeySerializer : KSerializer<StringResource> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("StringResource", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StringResource) {
        encoder.encodeString(value.key)
    }

    override fun deserialize(decoder: Decoder): StringResource {
        val key = decoder.decodeString()
        // todo
        throw IllegalArgumentException()
//        return Res.allStringResources[key]!!
    }
}