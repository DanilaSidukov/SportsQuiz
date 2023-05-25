package com.example.sportsquiz.features

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsquiz.R
import com.example.sportsquiz.core.score.ScoreViewModel
import com.example.sportsquiz.core.db.EntityWallpaper
import com.example.sportsquiz.core.di.ScoreViewModelFactory
import com.example.sportsquiz.core.di.WallpaperViewModelFactory
import com.example.sportsquiz.core.wallpaper.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class WallpaperActivity: AppCompatActivity(), OnWallpaperItemClicked {

    private val wallpaperAdapter = WallpaperAdapter(emptyList(), this, this)
    private lateinit var wallpaperRecyclerView: RecyclerView
    private lateinit var score: TextView

    private lateinit var wallpaperViewModel: WallpaperViewModel
    private var listOfWallpapers: List<EntityWallpaper> = emptyList()

    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        wallpaperViewModel = ViewModelProvider(this, WallpaperViewModelFactory())[WallpaperViewModel::class.java]
        scoreViewModel = ViewModelProvider(this, ScoreViewModelFactory())[ScoreViewModel::class.java]

        score = findViewById(R.id.score)
        wallpaperRecyclerView = findViewById(R.id.wallpaper_recycler_view)
        wallpaperRecyclerView.adapter = wallpaperAdapter
        wallpaperRecyclerView.layoutManager = GridLayoutManager(this, 3)
        wallpaperRecyclerView.addItemDecoration(GridLayoutItemDecoration(20))

        lifecycleScope.launch {
            scoreViewModel.score.collect{
                score.text = it.scoreValue.toString()
            }
        }

        lifecycleScope.launch {
            wallpaperViewModel.wallpaperList.collectLatest { list ->
                if (list.isEmpty()) return@collectLatest
                else {
                    listOfWallpapers = list
                    wallpaperAdapter.updateList(listOfWallpapers)
                }
            }
        }
    }

    override fun onWallpaperItemClickListener(entityWallpaper: EntityWallpaper) {
        AlertDialog.Builder(this)
            .setTitle("Buy wallpaper")
            .setMessage("Are you sure you want to buy wallpaper?")
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                lifecycleScope.launch {
                    if (entityWallpaper.isPurchased) Toast.makeText(
                        this@WallpaperActivity, "You have already purchased this wallpaper", Toast.LENGTH_SHORT).show()
                    else {
                        wallpaperViewModel.spendScore(entityWallpaper)
                        wallpaperViewModel.isSuccessful.collect{
                            if (!it) Toast.makeText(
                                this@WallpaperActivity, "Not enough score", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }

    fun setDeviceWallpaper(wallpaper: String): Bitmap {
        val assetsManager = this.resources.assets
        val inputStream = assetsManager.open(wallpaper)
        return BitmapFactory.decodeStream(inputStream)
    }

    override fun onWallpaperItemLongClickListener(wallpaperItem: EntityWallpaper) {

        AlertDialog.Builder(this)
            .setTitle("Set wallpaper")
            .setMessage("Are you sure you want to set background with this wallpaper?")
            .setPositiveButton(android.R.string.yes
            ) { dialog, which ->
                if (wallpaperItem.isPurchased) {
                    val wallpaperManager = WallpaperManager.getInstance(this@WallpaperActivity)
                    wallpaperManager.setBitmap(setDeviceWallpaper(wallpaperItem.drawable))
                } else Toast.makeText(
                    this@WallpaperActivity, "You didn't buy this wallpaper", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }

}