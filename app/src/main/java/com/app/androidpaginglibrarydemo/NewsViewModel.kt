package com.app.androidpaginglibrarydemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class NewsViewModel : ViewModel() {

    private lateinit var newsLiveData: LiveData<PagedList<News>>

    init {
        initialize()
    }

    private fun initialize() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        newsLiveData = LivePagedListBuilder(
            NewsDataFactory(),
            pagedListConfig
        ).setBoundaryCallback(object :PagedList.BoundaryCallback<News>(){
            override fun onItemAtEndLoaded(itemAtEnd: News) {
                super.onItemAtEndLoaded(itemAtEnd)
            }

            override fun onItemAtFrontLoaded(itemAtFront: News) {
                super.onItemAtFrontLoaded(itemAtFront)
            }
        })
            //    .setFetchExecutor(executor)
            .build()
    }

    fun getNewsLiveData(): LiveData<PagedList<News>> {
        return newsLiveData
    }
}