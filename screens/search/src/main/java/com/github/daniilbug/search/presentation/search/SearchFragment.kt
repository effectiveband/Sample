package com.github.daniilbug.search.presentation.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.daniilbug.core.base.viewBinding
import com.github.daniilbug.core.entity.ErrorReason
import com.github.daniilbug.coreui.extensions.onStateUpdate
import com.github.daniilbug.newsapi.domain.NewsLoadException
import com.github.daniilbug.search.R
import com.github.daniilbug.search.databinding.FragmentSearchBinding
import com.github.daniilbug.search.presentation.adapter.SearchAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding { FragmentSearchBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchAdapter(
            onClick = { item -> viewModel.sendEvent(SearchEvent.OpenDetails(item)) }
        )
        with(binding) {
            searchRecycler.adapter = adapter
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.sendEvent(SearchEvent.Search(newText.orEmpty()))
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean = false
            })
        }
        adapter.onStateUpdate(
            onError = { ex -> (ex as? NewsLoadException)?.reason.let(::setError) },
            onInitialLoading = { setLoading() },
            onNotLoading = { setNotLoading() }
        )
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state -> setState(adapter, state) }
            }
        }
    }

    private suspend fun setState(adapter: SearchAdapter, state: SearchState) {
        when (state) {
            SearchState.Empty -> setEmpty()
            is SearchState.News -> adapter.submitData(state.news)
        }
    }

    private fun setEmpty() {
        with(binding) {
            searchRecycler.isVisible = false
            searchCenteredLayout.isVisible = true
            searchProgressBar.isVisible = false
            searchCenteredImage.setImageResource(R.drawable.ic_search)
            searchCenteredText.setText(R.string.search_empty)
            searchRecycler.scrollToPosition(0)
        }
    }

    private fun setLoading() {
        with(binding) {
            searchRecycler.isVisible = false
            searchCenteredLayout.isVisible = false
            searchProgressBar.isVisible = true
        }
    }

    private fun setNotLoading() {
        with(binding) {
            searchRecycler.isVisible = true
            searchCenteredLayout.isVisible = false
            searchProgressBar.isVisible = false
        }
    }

    private fun setError(reason: ErrorReason?) {
        with(binding) {
            searchRecycler.isVisible = false
            searchCenteredLayout.isVisible = true
            searchProgressBar.isVisible = false
            searchCenteredImage.setImageResource(R.drawable.ic_not_found)
            searchCenteredText.text = reason?.message ?: getString(R.string.unexpected_error)
        }
    }
}