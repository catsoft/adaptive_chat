package com.catsoft.adaptivechat.ui.kit.api.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlin.plus

object ThrowableSerializer : KSerializer<Throwable> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("kotlin.Throwable") {
        element<String>("type") // Simple class name
        element<String?>("message", isOptional = true)
        element<Throwable?>("cause", isOptional = true) // Recursive
    }

    override fun serialize(encoder: Encoder, value: Throwable) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value::class.simpleName ?: value::class.toString())
            value.message?.let { encodeStringElement(descriptor, 1, it) }

            if (value.cause != null && value.cause != value) { // Basic check for self-reference
                try {
                    encodeNullableSerializableElement(descriptor, 2, ThrowableSerializer, value.cause)
                } catch (e: Exception) {
                    val causeSummary = value.cause!!::class.simpleName + ": " + value.cause!!.message
                    encodeStringElement(descriptor, 2, "Cause: $causeSummary [Simplified due to nesting or type]")
                }
            } else if (value.cause == value) {
                encodeStringElement(descriptor, 2, "Self-referential cause [Omitted]")
            } else {
                encodeNullableSerializableElement(descriptor, 2, ThrowableSerializer, null)
            }
        }
    }

    override fun deserialize(decoder: Decoder): Throwable {
        var type = "UnknownThrowable"
        var message: String? = null
        var cause: Throwable? = null

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> type = decodeStringElement(descriptor, 0)
                    1 -> message = decodeNullableSerializableElement(descriptor, 1, String.Companion.serializer())
                    2 -> {
                        try {
                            cause = decodeNullableSerializableElement(descriptor, 2, ThrowableSerializer)
                        } catch (e: SerializationException) {
                            println("Could not deserialize cause directly, may have been a summary. Cause set to null. Error: $e")
                            cause = null // Or create a generic exception with the string if you can access it
                        }
                    }
                    CompositeDecoder.Companion.DECODE_DONE -> break // No more elements
                    else -> throw SerializationException("Unknown index $index")
                }
            }
        }
        val deserializedException = RuntimeException("[$type] $message", cause)
        return deserializedException
    }
}