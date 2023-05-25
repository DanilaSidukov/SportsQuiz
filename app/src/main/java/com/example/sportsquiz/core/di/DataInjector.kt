package com.example.sportsquiz.core.di

import android.content.Context
import androidx.room.Room
import com.example.sportsquiz.core.score.ScoreRepository
import com.example.sportsquiz.core.db.DatabaseScore
import com.example.sportsquiz.core.db.ScoreDao
import com.example.sportsquiz.core.db.WallpaperDao
import com.example.sportsquiz.core.questions.QuestionsRepository
import com.example.sportsquiz.core.wallpaper.WallpaperRepository
import com.example.sportsquiz.core.wallpaper.WallpaperSource


class DataInjector(context: Context) {

    private lateinit var databaseScore: DatabaseScore
    private var scoreDao: ScoreDao
    private var wallpaperDao: WallpaperDao

    val questionsRepository = QuestionsRepository()
    var wallpaperRepository: WallpaperRepository

    var scoreRepository: ScoreRepository

    init {
        databaseScore = Room.databaseBuilder(
            context,
            DatabaseScore::class.java,
            "scoredatabase"
        ).fallbackToDestructiveMigration()
            .build()

        scoreDao = databaseScore.daoScore()
        wallpaperDao = databaseScore.daoWallpaper()

        scoreRepository = ScoreRepository(scoreDao)

        wallpaperRepository = WallpaperRepository(wallpaperDao, WallpaperSource())

    }


}

