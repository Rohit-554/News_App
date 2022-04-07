package com.example.news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),Newsitemclicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle_view.layoutManager=LinearLayoutManager(this)
        val items = fetchdata()
        val adapter= NewsListAdapter(items,this)
        recycle_view.adapter = adapter
    }

    private fun fetchdata():ArrayList<String> {
    val list = ArrayList<String>()
        for(i in 0 until 100){
            list.add("item is $i")
        }
        return list
    }

    override fun onitemclicked(item: String) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
    }
}