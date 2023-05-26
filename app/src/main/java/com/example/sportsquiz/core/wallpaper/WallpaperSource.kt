package com.example.sportsquiz.core.wallpaper

import com.example.sportsquiz.core.db.EntityWallpaper

class WallpaperSource() {
    fun getAllWallpapers(): List<EntityWallpaper>  {
        return listOf(
            EntityWallpaper(
                "one",
                "one_background.jpg",
                false
            ),
            EntityWallpaper(
                "two",
                "two_background.jpg",
                false
            ),
            EntityWallpaper(
                "three",
                "three_background.jpg",
                false
            ),
            EntityWallpaper(
                "four",
                "four_background.jpg",
                false
            ),
            EntityWallpaper(
                "five",
                "five_background.jpg",
                false
            ),
            EntityWallpaper(
                "six",
                "six_background.jpg",
                false
            ),
            EntityWallpaper(
                "seven",
                "seven_background.jpg",
                false
            ),
            EntityWallpaper(
                "eight",
                "eight_background.jpg",
                false
            ),
            EntityWallpaper(
                "nine",
                "nine_background.jpg",
                false
            ),
            EntityWallpaper(
                "ten",
                "ten_background.jpg",
                false
            )
        )
    }

}