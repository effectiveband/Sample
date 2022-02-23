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
import com.github.daniilbug.core.entity.ErrorReason
import com.github.daniilbug.coreui.extensions.onStateUpdate
import com.github.daniilbug.feed.R
import com.github.daniilbug.feed.databinding.FragmentFeedBinding
import com.github.daniilbug.feed.presentation.adapter.FeedAdapter
import com.github.daniilbug.newsapi.domain.NewsLoadException
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
        val adapter = FeedAdapter(
            onClick = { item -> viewModel.sendEvent(FeedEvent.OpenDetails(item)) }
        )
        with(binding) {
            feedRecycler.adapter = adapter
        }
        adapter.onStateUpdate(
            onError = { ex -> (ex as? NewsLoadException)?.reason.let(::setError) },
            onInitialLoading = { setLoading() },
            onNotLoading = { setNotLoading() }
        )
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.headlines.collectLatest { headlines ->
                    adapter.submitData(headlines)
                }
            }
        }
    }

    private fun setLoading() {
        with(binding) {
            feedRecycler.isVisible = false
            feedProgressBar.isVisible = true
            feedCenteredLayout.isVisible = false
        }
    }

    private fun setError(reason: ErrorReason?) {
        with(binding) {
            feedRecycler.isVisible = false
            feedProgressBar.isVisible = false
            feedCenteredLayout.isVisible = true
            feedCenteredText.text = reason?.message ?: getString(R.string.unexpected_error)
            feedCenteredImage.setImageResource(R.drawable.ic_error)
        }
    }

    private fun setNotLoading() {
        with(binding) {
            feedRecycler.isVisible = true
            feedProgressBar.isVisible = false
            feedCenteredLayout.isVisible = false
        }
    }
}
