package com.github.daniilbug.core.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class BaseSavedStateViewModelFactory<T>(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
    val creator: (handle: SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        try {
            @Suppress("UNCHECKED_CAST") return creator(handle) as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
