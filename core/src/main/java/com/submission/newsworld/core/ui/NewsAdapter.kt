package com.submission.newsworld.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.newsworld.core.R
import com.submission.newsworld.core.domain.model.News
import com.submission.newsworld.core.databinding.ItemListNewsBinding
import java.util.ArrayList

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_news, parent, false))

    override fun getItemCount()= listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListNewsBinding.bind(itemView)
        fun bind(data: News) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.pubDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }




}