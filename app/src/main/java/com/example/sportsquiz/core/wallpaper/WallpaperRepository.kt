package com.example.sportsquiz.core.wallpaper

import com.example.sportsquiz.core.db.EntityWallpaper
import com.example.sportsquiz.core.db.WallpaperDao
import kotlinx.coroutines.flow.onStart


class WallpaperRepository(
    private val wallpaperDao: WallpaperDao,
    private val wallpaperSource: WallpaperSource,
) {

    fun getWallpaperStat() = wallpaperDao.getWallpaperStat().onStart {
        emit(getWallpapers())
    }

    suspend fun changeWallpaperItem(item: EntityWallpaper) = wallpaperDao.changeData(item)

    fun getWallpapers(): List<EntityWallpaper> {
        return wallpaperSource.getAllWallpapers()
    }

}