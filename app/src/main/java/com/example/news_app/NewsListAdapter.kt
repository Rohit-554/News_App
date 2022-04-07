package com.example.news_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val items: ArrayList<String>, private val listener: MainActivity): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newsitemview,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onitemclicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentitem = items[position]
        holder.titleview.text = currentitem
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
//view holder
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
val titleview: TextView = itemView.findViewById(R.id.title)
}

interface Newsitemclicked{
    fun onitemclicked(item:String)
}

