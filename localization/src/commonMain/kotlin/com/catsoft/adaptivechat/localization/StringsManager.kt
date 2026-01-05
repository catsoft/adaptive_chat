package com.catsoft.adaptivechat.localization

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

interface StringsManager {
    suspend fun getString(resId: StringResource): String

    suspend fun getString(resId: StringResource, vararg formatArgs: Any): String
}

class StringsManagerImpl : StringsManager {
    override suspend fun getString(resId: StringResource): String = org.jetbrains.compose.resources.getString(resId)

    override suspend fun getString(resId: StringResource, vararg formatArgs: Any): String = org.jetbrains.compose.resources.getString(resId, *formatArgs)
}

