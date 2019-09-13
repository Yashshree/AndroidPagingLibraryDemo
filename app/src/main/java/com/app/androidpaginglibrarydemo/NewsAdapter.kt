package com.app.androidpaginglibrarydemo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_news_item_layout.view.*

class NewsAdapter() : PagedListAdapter<News, RecyclerView.ViewHolder>(NewsDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_news_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val new = getItem(position)
        holder.itemView.title.text=new!!.title

        Log.e("item count",getItemCount().toString())


    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}