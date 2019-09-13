package com.app.androidpaginglibrarydemo

import androidx.recyclerview.widget.DiffUtil

class NewsDiffUtilCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title.equals(newItem.title)
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title==newItem.title
    }
}