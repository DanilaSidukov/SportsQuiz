package com.example.sportsquiz.core.di

import com.example.sportsquiz.core.questions.QuestionsRepository
import com.example.sportsquiz.core.questions.QuestionsViewModel
import com.example.sportsquiz.core.wallpaper.WallpaperRepository
import com.example.sportsquiz.core.wallpaper.WallpaperSource
import com.example.sportsquiz.core.wallpaper.WallpaperViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::WallpaperRepository)
    single { QuestionsRepository() }
}

val viewModelModule = module {
    factory {
        WallpaperViewModel(get())
        QuestionsViewModel(get())
    }
}

val wallpaperSourceModule = module {
    factory { WallpaperSource(get()) }
}

