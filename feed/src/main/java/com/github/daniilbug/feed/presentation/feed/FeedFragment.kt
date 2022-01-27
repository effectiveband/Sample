package com.github.daniilbug.feed.presentation.feed

import android.os.Bundle
import android.view.View
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
                viewModel.headlines.collectLatest { headlines ->
                    adapter.submitData(headlines)
                }
            }
        }
    }
}