package com.example.news_app

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),Newsitemclicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle_view.layoutManager = LinearLayoutManager(this)
        fetchdata()
        mAdapter = NewsListAdapter(this)
        recycle_view.adapter = mAdapter
    }

    private fun fetchdata() {
        val url =
            "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=137d9a7a5a9947ebbed7ffe5a9c73e24"
        val getRequest: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsarray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                    )
                    newsarray.add(news)
                }
                mAdapter.updateNews(newsarray)
            },
            {

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params

            }

        }

        MySingleton.getInstance(this).addToRequestQueue(getRequest)

    }

    override fun onitemclicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val CustomTabsIntent = builder.build();
        CustomTabsIntent.launchUrl(this, (Uri.parse(item.url)))
        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(Color.parseColor("#000000"))
            .build()
        builder.setDefaultColorSchemeParams(defaultColors)
    }
}