package com.example.newsapp_train

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val itemclickListener:onitemClicked):RecyclerView.Adapter<MyViewHolder>() {
    private val items = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_items,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
           val currentItem = items[position]
            holder.tvTitle.text = currentItem.title
            holder.tvAuthor.text = currentItem.author
            holder.itemView.setOnClickListener{
                itemclickListener.onNewsClicked(currentItem.url)
            }
            Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.tvImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(itemsList:List<Article>){
        this.items.clear()
        this.items.addAll(itemsList)
        notifyDataSetChanged()
    }


}
class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvImage: ImageView = itemView.findViewById(R.id.newsPhoto)
    var tvTitle: TextView = itemView.findViewById(R.id.title)
    var tvAuthor: TextView = itemView.findViewById(R.id.author)

}
interface onitemClicked{
    fun onNewsClicked(url:String)
}
