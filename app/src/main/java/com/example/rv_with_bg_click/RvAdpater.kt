package com.example.rv_with_bg_click

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


const val TAG = "RvAdpater"

class RvAdpater : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener:OnItemClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewNormal = LayoutInflater.from(parent.context).inflate(R.layout.item_normal, parent, false)
        val viewTransparent = LayoutInflater.from(parent.context).inflate(R.layout.item_transparent, parent, false)

        return when(viewType){
            0 -> NormalViewHolder(viewNormal)
            2 -> TransparentViewHolder(viewTransparent)
            else -> NormalViewHolder(viewNormal)
        }

    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            0 -> {
                val normalHolder = holder as NormalViewHolder
                normalHolder.tv.text = "Post"
                normalHolder.itemView.setOnClickListener {
                    Log.d(TAG, "Clicked on Normal item")
                }
            }
            2 -> {
                val transparentHolder = holder as TransparentViewHolder
                transparentHolder.itemView.setOnClickListener {

                    listener.onClick()
                }
            }
        }
    }

    fun setListener(onItem:OnItemClick){
        listener = onItem
    }

    interface OnItemClick{

        fun onClick()
    }

    override fun getItemViewType(position: Int): Int = position % 2 * 2

    class NormalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv:TextView = itemView.findViewById(R.id.tv_post)
    }

    class TransparentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}