package com.github.daniilbug.feed.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.daniilbug.feed.databinding.ItemNewsBinding
import com.github.daniilbug.feed.presentation.feed.NewsItemUI

class FeedAdapter : ListAdapter<NewsItemUI, FeedAdapter.ViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<NewsItemUI>() {
        override fun areItemsTheSame(oldItem: NewsItemUI, newItem: NewsItemUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItemUI, newItem: NewsItemUI): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItemUI) {
            with(binding) {

            }
        }
    }
}