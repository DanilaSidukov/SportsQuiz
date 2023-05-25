package com.example.sportsquiz.core.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EntityScore::class, EntityWallpaper::class], version = 1)
abstract class DatabaseScore: RoomDatabase() {

    abstract fun daoScore(): ScoreDao

    abstract fun daoWallpaper(): WallpaperDao

}