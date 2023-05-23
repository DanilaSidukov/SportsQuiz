package com.example.sportsquiz.core.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityWallpaper(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "wallpaper") val wallpaper: Int,
    @ColumnInfo(name = "isPurchased") var isPurchased: Boolean
)