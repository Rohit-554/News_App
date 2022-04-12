package com.example.news_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: MainActivity): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
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
        holder.titleview.text = currentitem.title
        holder.author.text = currentitem.author
        Glide.with(holder.itemView.context).load(currentitem.imageurl).into(holder.image)
    }

    fun updateNews(updateNews:ArrayList<News>){
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
//view holder
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
val titleview: TextView = itemView.findViewById(R.id.title)
val image  : ImageView = itemView.findViewById(R.id.image)
val author : TextView = itemView.findViewById(R.id.author)
}

interface Newsitemclicked{
    fun onitemclicked(item:News)
}

