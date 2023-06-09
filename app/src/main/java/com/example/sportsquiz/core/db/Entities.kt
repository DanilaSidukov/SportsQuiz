package com.example.sportsquiz.core.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityWallpaper(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "wallpaper") val drawable: String,
    @ColumnInfo(name = "isPurchased") var isPurchased: Boolean
)

@Entity
data class EntityScore(
    @PrimaryKey val name: String = "score",
    @ColumnInfo(name = "totalScore") var scoreValue: Int = 0
)