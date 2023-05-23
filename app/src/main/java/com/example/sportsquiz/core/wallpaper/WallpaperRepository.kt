package com.example.sportsquiz.core.wallpaper

class WallpaperRepository(
    //val wallpaperDao: WallpaperDao
    private val wallpaperSource: WallpaperSource
) {

    //suspend fun getAllWallpapers() = wallpaperDao.getAll()

    //suspend fun changeWallpaperItem(item: EntityWallpaper) = wallpaperDao.changeData(item)

    suspend fun getWallpapers(): List<Wallpaper>{
        return wallpaperSource.getAllWallpapers()
    }

}