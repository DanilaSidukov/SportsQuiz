package com.example.sportsquiz.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WallpaperDao {

    @Query("SELECT * from entitywallpaper")
    suspend fun getAll(): List<EntityWallpaper>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeData(itemData: EntityWallpaper)

}