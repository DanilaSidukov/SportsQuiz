package com.example.sportsquiz.core.wallpaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsquiz.R

class WallpaperAdapter(
    private var wallpaperList: List<Wallpaper>,
) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int = wallpaperList.size

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.wallpaper.setImageDrawable(wallpaperList[position].drawable)

    }

    class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var wallpaper: ImageView = itemView.findViewById(R.id.image_wallpaper)
        val purchasedCondition: ImageView = itemView.findViewById(R.id.icon_check)
    }

    fun updateList(list: List<Wallpaper>){
        wallpaperList = list
        notifyDataSetChanged()
    }

}