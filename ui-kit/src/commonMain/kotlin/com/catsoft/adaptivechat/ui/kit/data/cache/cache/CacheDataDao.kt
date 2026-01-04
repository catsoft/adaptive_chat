package com.catsoft.adaptivechat.ui.kit.data.cache.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDataDao {
    @Query("SELECT * FROM cache_data WHERE id = :param LIMIT 1")
    suspend fun getData(param: String): CacheData?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertData(data: CacheData)

    @Query("DELETE FROM cache_data")
    suspend fun deleteAll()
}

