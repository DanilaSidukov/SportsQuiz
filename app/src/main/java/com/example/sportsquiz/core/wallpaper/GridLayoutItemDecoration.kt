package com.example.sportsquiz.core.wallpaper

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class GridLayoutItemDecoration(
    private val spaceSize: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            parent.getChildAdapterPosition(view).let {position ->
                    top = (spaceSize * Resources.getSystem().displayMetrics.density).roundToInt()
                    bottom = (spaceSize * Resources.getSystem().displayMetrics.density).roundToInt()
            }
        }
    }
}