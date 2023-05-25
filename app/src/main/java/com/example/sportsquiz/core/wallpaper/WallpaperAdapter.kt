package com.example.sportsquiz.core.wallpaper

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsquiz.R
import com.example.sportsquiz.core.db.EntityWallpaper

class WallpaperAdapter(
    private var wallpaperList: List<EntityWallpaper>,
    private val context: Context,
    private var listener: OnWallpaperItemClicked
) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        return WallpaperViewHolder(view)
    }

    override fun getItemCount(): Int = wallpaperList.size

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {

        val item =  wallpaperList[position]

        holder.itemView.setOnClickListener {
            listener.onWallpaperItemClickListener(item)
            updateList(wallpaperList)
        }

        holder.itemView.setOnLongClickListener {
            listener.onWallpaperItemLongClickListener(item)
            return@setOnLongClickListener true
        }

        holder.wallpaper.setImageDrawable(getPath(wallpaperList[position].drawable))
        if (wallpaperList[position].isPurchased) holder.purchasedCondition.visibility = View.VISIBLE
    }

    class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var wallpaper: ImageView = itemView.findViewById(R.id.image_wallpaper)
        val purchasedCondition: ImageView = itemView.findViewById(R.id.icon_check)
    }

    fun updateList(list: List<EntityWallpaper>) {
        wallpaperList = list
        notifyDataSetChanged()
    }

    private fun getPath(path: String): Drawable {
        val assetsManager = context.resources.assets
        val inputStream = assetsManager.open(path)
        return Drawable.createFromStream(inputStream, null)!!
    }

}

interface OnWallpaperItemClicked{

    fun onWallpaperItemClickListener(entityWallpaper: EntityWallpaper)

    fun onWallpaperItemLongClickListener(wallpaperItem: EntityWallpaper)

}
