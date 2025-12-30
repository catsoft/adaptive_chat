package com.catsoft.adaptivechat.platform

expect class FileHandler() {
    suspend fun pickImage(): ByteArray?
    suspend fun pickDocument(): Pair<ByteArray, String>?
    suspend fun recordAudio(): ByteArray?
}
