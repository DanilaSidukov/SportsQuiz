package com.example.sportsquiz.core.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.core.db.EntityScore
import com.example.sportsquiz.core.db.EntityWallpaper
import com.example.sportsquiz.core.score.ScoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WallpaperViewModel(
    private val wallpaperRepository: WallpaperRepository,
    private val scoreRepository: ScoreRepository,
) : ViewModel() {

    private var _wallpaperList = MutableStateFlow<List<EntityWallpaper>>(emptyList())
    var wallpaperList = _wallpaperList.asStateFlow()

    init {
        viewModelScope.launch {
            if (wallpaperRepository.checkIsEmpty()) {
                _wallpaperList.value = wallpaperRepository.getWallpapers()
                _wallpaperList.value.forEach {
                    wallpaperRepository.changeWallpaperItem(it)
                }
            } else _wallpaperList.emit(
                wallpaperRepository.getWallpaperStat().first()
            )
        }
    }

    val _isSuccessful = MutableStateFlow<Boolean?>(null)
    var isSuccessful = _isSuccessful.asStateFlow()

    fun spendScore(item: EntityWallpaper) {
        viewModelScope.launch {
            scoreRepository.getTotalScore().first().let {
                if (it.scoreValue >= 3) {
                    scoreRepository.changeTotalScore(
                        EntityScore(
                            "score",
                            it.scoreValue - 3
                        )
                    )
                    wallpaperRepository.changeWallpaperItem(
                        EntityWallpaper(
                            item.name,
                            item.drawable,
                            true
                        )
                    )
                    wallpaperRepository.getWallpaperStat().collectLatest { newList ->
                        _wallpaperList.emit(newList)
                    }
                    _isSuccessful.emit(true)
                } else _isSuccessful.emit(false)
            }
        }
    }
}