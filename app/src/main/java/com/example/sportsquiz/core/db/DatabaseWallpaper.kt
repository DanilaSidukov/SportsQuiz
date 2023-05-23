package com.example.sportsquiz.core.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EntityWallpaper::class], version = 1)
abstract class DatabaseWallpaper: RoomDatabase() {

    abstract fun daoWallpaper(): WallpaperDao

}