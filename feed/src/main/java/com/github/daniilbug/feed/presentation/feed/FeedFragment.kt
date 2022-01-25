package com.github.daniilbug.feed.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.daniilbug.core.base.viewBinding
import com.github.daniilbug.feed.R
import com.github.daniilbug.feed.databinding.FragmentFeedBinding
import com.github.daniilbug.feed.presentation.adapter.FeedAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_feed) {

    private val viewModel: FeedViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding { FragmentFeedBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FeedAdapter(onClick = { })
        with(binding) {
            feedRecycler.adapter = adapter
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state -> setState(adapter, state) }
            }
        }
    }

    private fun setState(adapter: FeedAdapter, state: FeedState) {
        when(state) {
            FeedState.Loading -> setLoading()
            FeedState.NetworkError -> TODO()
            is FeedState.UnexpectedError -> TODO()
            is FeedState.Headlines -> setHeadlines(adapter, state.items)
        }
    }

    private fun setLoading() {
        with(binding) {
            feedProgressBar.isVisible = true
            feedRecycler.isVisible = false
        }
    }

    private fun setHeadlines(adapter: FeedAdapter, items: List<HeadlinesItemUI>) {
        with(binding) {
            feedProgressBar.isVisible = false
            feedRecycler.isVisible = true
            adapter.submitList(items)
        }
    }
}