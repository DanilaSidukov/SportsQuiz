package com.example.sportsquiz.core.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpaperDao {

    @Query("SELECT * from entitywallpaper")
    fun getWallpaperStat(): Flow<List<EntityWallpaper>>

    @Upsert
    suspend fun changeData(itemData: EntityWallpaper)

    @Query("SELECT (SELECT COUNT(*) FROM entitywallpaper) == 0")
    suspend fun checkIsEmpty(): Boolean

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
