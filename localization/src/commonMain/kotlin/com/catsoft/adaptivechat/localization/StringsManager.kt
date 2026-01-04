package com.catsoft.adaptivechat.localization

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

interface StringsManager {
    suspend fun getString(resId: StringResource, args: List<String> = emptyList()): String
}

class StringsManagerImpl : StringsManager {
    override suspend fun getString(resId: StringResource, args: List<String>): String {
        return if (args.isEmpty()) {
            getString(resId)
        } else {
            getString(resId, *args.toTypedArray())
        }
    }
}
