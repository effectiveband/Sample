package com.github.daniilbug.feed.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.daniilbug.feed.databinding.ItemHeadlinesBinding
import com.github.daniilbug.feed.presentation.feed.HeadlinesItemUI

class FeedAdapter(
    private val onClick: OnClickListener
) : ListAdapter<HeadlinesItemUI, FeedAdapter.ViewHolder>(DiffCallback) {

    fun interface OnClickListener {
        fun onHeadlinesItemClick(item: HeadlinesItemUI)
    }

    private object DiffCallback : DiffUtil.ItemCallback<HeadlinesItemUI>() {

        override fun areItemsTheSame(
            oldItem: HeadlinesItemUI,
            newItem: HeadlinesItemUI
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HeadlinesItemUI,
            newItem: HeadlinesItemUI
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeadlinesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                onClick.onHeadlinesItemClick(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHeadlinesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeadlinesItemUI) {
            with(binding) {
                itemHeadlinesSourceLetterText.text = item.source.first().toString()
                itemHeadlinesTitle.text = item.title
                itemHeadlinesSource.text = item.source
            }
        }
    }
}