package com.catsoft.adaptivechat.ui.kit.data.cache.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CacheData::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDataDao
}