package com.github.daniilbug.coreui.extensions

import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

fun PagingDataAdapter<out Any, out RecyclerView.ViewHolder>.onStateUpdate(
    onError: (error: Throwable) -> Unit,
    onLoading: () -> Unit = { },
    onNotLoading: () -> Unit = { }
) {
    addLoadStateListener { states ->
        when (val append = states.refresh) {
            is LoadState.Error -> onError(append.error)
            LoadState.Loading -> onLoading()
            is LoadState.NotLoading -> onNotLoading()
        }
    }
}