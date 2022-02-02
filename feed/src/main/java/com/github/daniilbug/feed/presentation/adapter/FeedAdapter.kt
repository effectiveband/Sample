package com.github.daniilbug.feed.presentation.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.daniilbug.coreui.themeColor
import com.github.daniilbug.feed.R
import com.github.daniilbug.feed.databinding.ItemHeadlinesBinding
import com.github.daniilbug.feed.presentation.feed.HeadlinesItemUI
import kotlin.random.Random

class FeedAdapter(
    private val onClick: OnClickListener
) : PagingDataAdapter<HeadlinesItemUI, FeedAdapter.ViewHolder>(DiffCallback) {

    fun interface OnClickListener {
        fun onHeadlinesItemClick(item: HeadlinesItemUI)
    }

    private object DiffCallback : DiffUtil.ItemCallback<HeadlinesItemUI>() {

        override fun areItemsTheSame(
            oldItem: HeadlinesItemUI,
            newItem: HeadlinesItemUI
        ): Boolean {
            return oldItem.title == newItem.title
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
                getItem(bindingAdapterPosition)?.let(onClick::onHeadlinesItemClick)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHeadlinesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeadlinesItemUI?) {
            with(binding) {
                if (item == null) {
                    bindPlaceholder()
                } else {
                    bindItem(item)
                }
            }
        }

        private fun ItemHeadlinesBinding.bindPlaceholder() {
            itemHeadlinesSourceLetterText.text = "\t"
            itemHeadlinesTitle.text = "\t".repeat(Random.nextInt(10, 20))
            itemHeadlinesSource.text = "\t".repeat(Random.nextInt(10, 20))
            itemHeadlinesDate.text = "\t".repeat(10)
            val placeholderColor = root.context.themeColor(R.attr.colorSecondary)
            itemHeadlinesTitle.setBackgroundColor(placeholderColor)
            itemHeadlinesSource.setBackgroundColor(placeholderColor)
            itemHeadlinesDate.setBackgroundColor(placeholderColor)
        }

        private fun ItemHeadlinesBinding.bindItem(item: HeadlinesItemUI) {
            itemHeadlinesSourceLetterText.text = item.source.first().toString()
            itemHeadlinesTitle.text = item.title
            itemHeadlinesSource.text = item.source
            val relativeDateString = DateUtils.getRelativeTimeSpanString(
                item.date.time,
                System.currentTimeMillis(),
                DateUtils.DAY_IN_MILLIS
            )
            itemHeadlinesDate.text = relativeDateString
            itemHeadlinesTitle.background = null
            itemHeadlinesSource.background = null
            itemHeadlinesDate.background = null
        }
    }
}