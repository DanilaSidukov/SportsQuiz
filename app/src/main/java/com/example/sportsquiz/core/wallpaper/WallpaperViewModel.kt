package com.example.sportsquiz.core.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsquiz.core.db.EntityWallpaper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WallpaperViewModel(
    private val wallpaperRepository: WallpaperRepository
) : ViewModel() {

    private val _wallpaperList = MutableStateFlow<List<EntityWallpaper>>(emptyList())
    var wallpaperList =_wallpaperList.asStateFlow()

    private val _allWallpapers = MutableStateFlow<List<Wallpaper>>(emptyList())
    var allWallpapers = _allWallpapers.asStateFlow()

    init {

        viewModelScope.launch {
            _allWallpapers.emit(
                wallpaperRepository.getWallpapers()
            )
        }
    }


}