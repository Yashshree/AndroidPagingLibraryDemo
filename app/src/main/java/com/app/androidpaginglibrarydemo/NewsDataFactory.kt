package com.app.androidpaginglibrarydemo

import androidx.paging.DataSource

class NewsDataFactory() :
    DataSource.Factory<Int, News>() {

    override fun create(): DataSource<Int, News> {
        return NewsDataSource()
    }
}