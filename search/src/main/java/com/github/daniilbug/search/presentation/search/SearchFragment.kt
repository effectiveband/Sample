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
import androidx.paging.PagingData
import com.github.daniilbug.core.base.viewBinding
import com.github.daniilbug.search.R
import com.github.daniilbug.search.databinding.FragmentSearchBinding
import com.github.daniilbug.search.presentation.adapter.SearchAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SearchFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding { FragmentSearchBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchAdapter()
        with(binding) {
            searchRecycler.adapter = adapter
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.sendEvent(SearchEvent.Search(newText.orEmpty()))
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean = false
            })
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state -> setState(adapter, state) }
            }
        }
    }

    private suspend fun setState(adapter: SearchAdapter, state: SearchState) {
        when (state) {
            SearchState.Loading -> setLoading()
            is SearchState.News -> setNews(adapter, state.news)
        }
    }

    private fun setLoading() {

    }

    private suspend fun setNews(adapter: SearchAdapter, news: PagingData<SearchItemUI>) {
        adapter.submitData(news)
    }
}