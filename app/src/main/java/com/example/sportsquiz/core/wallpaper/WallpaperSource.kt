package com.example.sportsquiz.core.wallpaper

import android.content.Context
import android.graphics.drawable.Drawable

class WallpaperSource(
    private val context: Context
) {

    val assetsManager = context.resources.assets

    fun getAllWallpapers(): List<Wallpaper>{

        return listOf(
            Wallpaper(
                "one",
                getPath("one_background.jpg")
            ),
            Wallpaper(
                "two",
                getPath("two_background.jpg")
            ),
            Wallpaper(
                "three",
                getPath("three_background.jpg")
            ),
            Wallpaper(
                "four",
                getPath("four_background.jpg")
            ),
            Wallpaper(
                "five",
                getPath("five_background.jpg")
            ),
            Wallpaper(
                "six",
                getPath("six_background.jpg")
            ),
            Wallpaper(
                "seven",
                getPath("seven_background.jpg")
            ),
            Wallpaper(
                "eight",
                getPath("eight_background.jpg")
            ),
            Wallpaper(
                "nine",
                getPath("nine_background.jpg")
            ),
            Wallpaper(
                "ten",
                getPath("ten_background.jpg")
            )
        )
    }

    fun getPath(path: String): Drawable{
        val inputStream = assetsManager.open(path)
        return Drawable.createFromStream(inputStream, null)!!
    }

}