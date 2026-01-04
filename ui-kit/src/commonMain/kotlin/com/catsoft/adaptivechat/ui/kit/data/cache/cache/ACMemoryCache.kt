package com.catsoft.adaptivechat.ui.kit.data.cache.cache

class ACMemoryCache() {
    val cache: MutableMap<String, Any?> = mutableMapOf()

    fun clear() {
        cache.clear()
    }
}