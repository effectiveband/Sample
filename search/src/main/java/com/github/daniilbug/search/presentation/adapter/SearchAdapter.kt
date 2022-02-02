package com.github.daniilbug.search.presentation.adapter

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.daniilbug.coreui.extensions.load
import com.github.daniilbug.coreui.themeColor
import com.github.daniilbug.search.R
import com.github.daniilbug.search.databinding.ItemSearchBinding
import com.github.daniilbug.search.presentation.search.SearchItemUI
import kotlin.random.Random

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
            itemSearchImage.load(R.drawable.ic_image)
            itemSearchTitle.text = "\t".repeat(Random.nextInt(10, 20))
            itemSearchDescription.text = "\t".repeat(Random.nextInt(20, 60))
            itemSearchDateText.text = "\t".repeat(Random.nextInt(10, 20))
            itemSearchSourceText.text = "\t".repeat(Random.nextInt(10, 20))
            val placeholderColor = root.context.themeColor(R.attr.colorSecondary)
            itemSearchTitle.setBackgroundColor(placeholderColor)
            itemSearchDescription.setBackgroundColor(placeholderColor)
            itemSearchDateText.setBackgroundColor(placeholderColor)
            itemSearchSourceText.setBackgroundColor(placeholderColor)
        }

        @SuppressLint("CheckResult")
        private fun ItemSearchBinding.bindItem(item: SearchItemUI) {
            itemSearchImage.load(item.imageUrl) {
                this.placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
            }
            itemSearchTitle.text = item.title
            itemSearchDescription.text = item.description
            itemSearchSourceText.text = item.source
            itemSearchDateText.text = DateFormat.getDateFormat(root.context).format(item.date)
            itemSearchTitle.background = null
            itemSearchDescription.background = null
            itemSearchDateText.background = null
            itemSearchSourceText.background = null
        }
    }
}