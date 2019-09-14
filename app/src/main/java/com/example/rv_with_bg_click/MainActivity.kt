package com.example.rv_with_bg_click

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RvAdpater.OnItemClick {

    private lateinit var adView: View
    private lateinit var rvPosts: RecyclerView

    override fun onClick() {
        bringAdFront()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPosts = findViewById(R.id.rv_posts)
        rvPosts.layoutManager = LinearLayoutManager(this)

        val rvAdpater = RvAdpater()
        rvAdpater.setListener(this)
        rvPosts.adapter = rvAdpater



    }

    private fun bringAdFront() {

        adView = findViewById<View>(R.id.ad)
        adView.bringToFront()
    }

    override fun onBackPressed() {
        // to go back to the normal recycler view when back button is pressed
        val parent = rvPosts.parent as ViewGroup
        parent.removeAllViews()
        parent.addView(adView, 0)
        parent.addView(rvPosts, 1)
    }
}
