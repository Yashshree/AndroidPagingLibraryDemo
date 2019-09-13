package com.app.androidpaginglibrarydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel= ViewModelProviders.of(this).get(NewsViewModel::class.java)

        recyclerView.setLayoutManager( LinearLayoutManager(this))

        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val adapter = NewsAdapter()

        recyclerView.adapter=adapter


        viewmodel.getNewsLiveData().observe(this,object : Observer<PagedList<News>>{
            override fun onChanged(list: PagedList<News>?) {
                Log.e("fetched list size=","${list?.size}")
                adapter.submitList(list)

            }

        })

    }
}
