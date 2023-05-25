package com.example.sportsquiz.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpaperDao {

    @Query("SELECT * from entitywallpaper")
    fun getWallpaperStat(): Flow<List<EntityWallpaper>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeData(itemData: EntityWallpaper)

}

@Dao
interface ScoreDao {

    @Query("SELECT * from entityscore")
    fun getScore(): Flow<EntityScore>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeScore(itemData: EntityScore)

    @Query("SELECT (SELECT COUNT(*) FROM entityscore) == 0")
    suspend fun isEmpty(): Boolean
}
