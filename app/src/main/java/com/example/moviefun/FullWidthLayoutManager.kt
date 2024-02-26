package com.example.moviefun
import android.content.Context
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FullWidthLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        // Set the first item to occupy full width
        val firstChild = getChildAt(0)
        firstChild?.let {
            val lp = it.layoutParams as WindowManager.LayoutParams
            lp.width = width // Full width
            it.layoutParams = lp
        }
    }
}
