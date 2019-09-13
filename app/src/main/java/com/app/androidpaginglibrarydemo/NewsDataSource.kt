package com.app.androidpaginglibrarydemo

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


class NewsDataSource(val body:HashMap<String,String> = HashMap<String,String>()) : PageKeyedDataSource<Int,News>() {
    init {

        body["q"]="movies"
        body["apiKey"]="079dac74a5f94ebdb990ecf61c8854b7"
    }
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, News>
    ) {

        Log.e("======","load Initial called")

        val builder = AndroidNetworking.get("https://newsapi.org/v2/everything")
        body["pageSize"]=params.requestedLoadSize.toString()
        body["page"]="1"

        Log.e("body=","${body}")
        builder.addQueryParameter(body)

        builder.build().getAsString(object : StringRequestListener {
            override fun onResponse(response: String) {
//                listener.onSuccess(response)
                Log.e("======","success")

                val list : ArrayList<News> = GsonBuilder().create().fromJson(JSONObject(response).getString("articles").toString(), object : TypeToken<List<News>>() {

                }.type)
                callback.onResult(list,0,1)
            }

            override fun onError(anError: ANError?) {
                Log.e("======","error="+anError)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        Log.e("======","load after called")

        val builder = AndroidNetworking.get("https://newsapi.org/v2/everything")
       // body["pageSize"]="10"
        body["page"]=params.key.toString()

        Log.e("body=","${body}")
        builder.addQueryParameter(body)

        builder.build().getAsString(object : StringRequestListener {
            override fun onResponse(response: String) {
//                listener.onSuccess(response)
                Log.e("======","success")

                val list : ArrayList<News> = GsonBuilder().create().fromJson(JSONObject(response).getString("articles").toString(), object : TypeToken<List<News>>() {

                }.type)

                callback.onResult(list,params.key+1)
            }

            override fun onError(anError: ANError?) {
                Log.e("======","error="+anError?.message)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        Log.e("======","load before called")


    }


}