package com.example.newsapp_train

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(),onitemClicked {
    private lateinit var myAdapter: NewsAdapter
    private lateinit var recycleViewList:RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var newsList:ArrayList<Article>
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleViewList = findViewById(R.id.recycleViewList)
        recycleViewList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycleViewList.layoutManager = linearLayoutManager

        val helper = RetrofitHelper.getInstance().create(NewsApi::class.java)
        myAdapter = NewsAdapter(this)
        GlobalScope.launch {
            val result = helper.getNews("us","137d9a7a5a9947ebbed7ffe5a9c73e24")
            if (result.isSuccessful){
                val responseBody = result.body()?.articles
                if (responseBody != null) {
                    myAdapter.initData(responseBody)
                }
                withContext(Dispatchers.Main){
                    recycleViewList.adapter = myAdapter
                }

            }

        }

    }

    override fun onNewsClicked(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }


}