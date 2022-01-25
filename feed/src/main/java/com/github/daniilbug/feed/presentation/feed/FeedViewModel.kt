package com.github.daniilbug.feed.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class FeedViewModel @Inject constructor(): ViewModel() {

    private val mutableState = MutableStateFlow<FeedState>(FeedState.Loading)
    val state: StateFlow<FeedState> = mutableState.asStateFlow()

    init {
        loadHeadlines()
    }

    private fun loadHeadlines() = viewModelScope.launch {
        fun randomLetter() = Random.nextInt(until = 128).toChar()

        delay(500L)
        mutableState.value = FeedState.Headlines(
            List(10) { index ->
                HeadlinesItemUI(index.toString(), "Title $index", "${randomLetter()} source")
            }
        )
    }
}