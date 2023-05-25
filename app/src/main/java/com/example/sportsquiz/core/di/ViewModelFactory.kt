package com.example.sportsquiz.core.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sportsquiz.core.score.ScoreViewModel
import com.example.sportsquiz.core.score.Settings
import com.example.sportsquiz.core.SportsQuizApplication
import com.example.sportsquiz.core.questions.QuestionsViewModel
import com.example.sportsquiz.core.wallpaper.WallpaperViewModel


class QuestionViewModelFactory(
    private val context: Context,
    private val difficulty: Settings.DIFFICULTY
) : ViewModelProvider.NewInstanceFactory() {

    private val dataInjector = DataInjector(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == QuestionsViewModel::class.java) {
            QuestionsViewModel(
                SportsQuizApplication.getInjector().questionsRepository,
                SportsQuizApplication.getInjector().scoreRepository,
                difficulty
            ) as T
        } else throw IllegalArgumentException("model class $modelClass not found")
    }
}

class ScoreViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == ScoreViewModel::class.java) {
            ScoreViewModel(
                SportsQuizApplication.getInjector().scoreRepository,
            ) as T
        } else throw IllegalArgumentException("model class $modelClass not found")
    }

}

class WallpaperViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == WallpaperViewModel::class.java) {
            WallpaperViewModel(
                SportsQuizApplication.getInjector().wallpaperRepository,
                SportsQuizApplication.getInjector().scoreRepository
            ) as T
        } else throw IllegalArgumentException("model class $modelClass not found")
    }

}