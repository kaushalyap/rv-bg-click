package com.example.rv_with_bg_click

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity(), RvAdpater.OnItemClick {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adView: View
    private lateinit var rvPosts: RecyclerView

    override fun onClick() {
        bringAdFront()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupRecyclerView() {
        rvPosts = findViewById(R.id.rv_posts)
        rvPosts.layoutManager = LinearLayoutManager(this)

        val rvAdpater = RvAdpater()
        rvAdpater.setListener(this)
        rvPosts.adapter = rvAdpater
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_posts)
        swipeRefreshLayout.setOnRefreshListener {
            Log.d(TAG, "Refreshing...")
        }

    }

    private fun bringAdFront() {

        adView = findViewById<View>(R.id.ad)
        adView.bringToFront()
    }

    // to go back to the normal recycler view(which is inside swipe refresh layout)
    // when back button is pressed
    override fun onBackPressed() {
        val parent = swipeRefreshLayout.parent as ViewGroup
        parent.removeAllViews()
        parent.addView(adView, 0)
        // at last add swipe refresh layout which is the container of the recyclerview
        parent.addView(swipeRefreshLayout, 1)
    }
}
