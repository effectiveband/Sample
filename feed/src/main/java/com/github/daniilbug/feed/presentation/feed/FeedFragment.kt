package com.github.daniilbug.feed.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.daniilbug.core.base.viewBinding
import com.github.daniilbug.feed.R
import com.github.daniilbug.feed.databinding.FragmentFeedBinding
import com.github.daniilbug.feed.presentation.adapter.FeedAdapter
import javax.inject.Inject

class FeedFragment @Inject constructor() : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding { FragmentFeedBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FeedAdapter()
        with(binding) {
            feedRecycler.adapter = adapter
        }
        adapter.submitList(
            List(10) { index ->
                NewsItemUI(index.toString(), "Title $index", "Description $index", "")
            }
        )
    }
}