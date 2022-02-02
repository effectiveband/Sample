package com.github.daniilbug.coreui.extensions

import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

fun PagingDataAdapter<out Any, out RecyclerView.ViewHolder>.onStateUpdate(
    onError: (error: Throwable) -> Unit,
    onInitialLoading: () -> Unit = { },
    onNotLoading: () -> Unit = { }
) {
    addLoadStateListener { states ->
        when (val refresh = states.refresh) {
            is LoadState.Error -> onError(refresh.error)
            LoadState.Loading -> if (itemCount == 0) onInitialLoading()
            is LoadState.NotLoading -> onNotLoading()
        }
    }
}