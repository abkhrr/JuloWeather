package com.juloweather.utils.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.juloweather.utils.ext.properties.dp

enum class Orientation{
    HORIZONTAL, VERTICAL
}

class SpaceItemDecoration(
    private val orientation: Orientation,
    private val space: Int,
    private val spaceMiddle: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildLayoutPosition(view)
        val itemCount = state.itemCount

        when {
            itemCount == 1 -> {
                if (orientation == Orientation.HORIZONTAL) {
                    outRect.left = space.dp
                    outRect.right = space.dp
                } else {
                    outRect.top = space.dp
                    outRect.bottom = space.dp
                }
            }
            itemPosition == 0 -> {
                //first
                if (orientation == Orientation.HORIZONTAL) {
                    outRect.left = space.dp
                    outRect.right = spaceMiddle.dp
                } else {
                    outRect.top = space.dp
                    outRect.bottom = spaceMiddle.dp
                }
            }
            itemCount > 0 && itemPosition == itemCount - 1 -> {
                //last
                if (orientation == Orientation.HORIZONTAL) {
                    outRect.right = space.dp
                    outRect.left = spaceMiddle.dp
                } else {
                    outRect.top = space.dp
                    outRect.bottom = spaceMiddle.dp
                }
            }
            else -> {
                if (orientation == Orientation.HORIZONTAL) {
                    outRect.left = spaceMiddle.dp
                    outRect.right = spaceMiddle.dp
                } else {
                    outRect.top = space.dp
                    outRect.bottom = spaceMiddle.dp
                }
            }
        }
    }
}