package com.example.sportsquiz.features

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsquiz.R
import com.example.sportsquiz.core.wallpaper.GridLayoutItemDecoration
import com.example.sportsquiz.core.wallpaper.Wallpaper
import com.example.sportsquiz.core.wallpaper.WallpaperAdapter
import com.example.sportsquiz.core.wallpaper.WallpaperViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class WallpaperActivity: AppCompatActivity() {

    private val wallpaperAdapter = WallpaperAdapter(emptyList())
    private lateinit var wallpaperRecyclerView: RecyclerView
    private lateinit var score: TextView
    private val wallpaperViewModel: WallpaperViewModel by inject()
    private var listOfWallpapers: List<Wallpaper> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        score = findViewById(R.id.score)
        wallpaperRecyclerView = findViewById(R.id.wallpaper_recycler_view)
        wallpaperRecyclerView.adapter = wallpaperAdapter
        wallpaperRecyclerView.layoutManager = GridLayoutManager(this, 3)
        wallpaperRecyclerView.addItemDecoration(GridLayoutItemDecoration(20))


        lifecycleScope.launch {
            wallpaperViewModel.allWallpapers.collect {list ->
                if (list.isEmpty()) return@collect
                else {
                    println("list - $list")
                    listOfWallpapers = list
                    wallpaperAdapter.updateList(listOfWallpapers)
                }
            }
        }

    }

}