package com.github.daniilbug.feed.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.daniilbug.newsapi.data.ArticleDomain
import com.github.daniilbug.newsapi.domain.NewsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class FeedViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val headlines = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = true),
        pagingSourceFactory = { newsRepository.getHeadlines() }
    ).flow.cachedIn(viewModelScope).mapLatest { data ->
        data.map(ArticleDomain::asHeadlinesItemUI)
    }
}