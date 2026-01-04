package com.catsoft.adaptivechat.ui.kit.data.cache.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_data")
data class CacheData(
    @PrimaryKey val id: String,
    val content: String?
)

