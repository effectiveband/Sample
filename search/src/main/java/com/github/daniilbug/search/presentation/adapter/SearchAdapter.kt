package com.github.daniilbug.search.presentation.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.daniilbug.coreui.extensions.load
import com.github.daniilbug.search.databinding.ItemSearchBinding
import com.github.daniilbug.search.presentation.search.SearchItemUI

class SearchAdapter : PagingDataAdapter<SearchItemUI, SearchAdapter.ViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<SearchItemUI>() {
        override fun areItemsTheSame(oldItem: SearchItemUI, newItem: SearchItemUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItemUI, newItem: SearchItemUI): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchItemUI?) {
            with(binding) {
                if (item == null) {
                    bindPlaceholder()
                } else {
                    bindItem(item)
                }
            }
        }

        private fun ItemSearchBinding.bindPlaceholder() {
            // TODO add placeholder
        }

        private fun ItemSearchBinding.bindItem(item: SearchItemUI) {
            itemSearchImage.load(item.imageUrl)
            itemSearchTitle.text = item.title
            itemSearchDescription.text = item.description
            itemSearchSourceText.text = item.source
            itemSearchDateText.text = DateFormat.getDateFormat(root.context).format(item.date)
        }
    }
}