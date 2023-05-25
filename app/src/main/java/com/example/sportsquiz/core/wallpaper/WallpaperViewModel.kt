package com.example.sportsquiz.core.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.core.score.ScoreRepository
import com.example.sportsquiz.core.db.EntityScore
import com.example.sportsquiz.core.db.EntityWallpaper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WallpaperViewModel(
    private val wallpaperRepository: WallpaperRepository,
    private val scoreRepository: ScoreRepository,
) : ViewModel() {

    val wallpaperList = wallpaperRepository.getWallpaperStat().shareIn(
        viewModelScope,
        SharingStarted.Lazily
    )

    val _isSuccessful = MutableStateFlow(false)
    var isSuccessful = _isSuccessful.asStateFlow()

    fun spendScore(item: EntityWallpaper) {
        if (item.isPurchased) return
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
                    _isSuccessful.emit(true)
                } else _isSuccessful.emit(false)
            }
        }
    }


}