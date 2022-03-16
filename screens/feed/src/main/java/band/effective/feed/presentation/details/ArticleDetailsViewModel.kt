package band.effective.feed.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import band.effective.core.navigation.flow.FlowCoordinator
import band.effective.core.navigation.flow.NavigationFlow
import band.effective.mainNavigation.ArticleDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class ArticleDetailsViewModel @AssistedInject constructor(
    @Assisted articleDetails: ArticleDetails,
    private val flowCoordinator: FlowCoordinator
) : ViewModel() {

    @AssistedFactory
    interface Creator {
        fun create(details: ArticleDetails): ArticleDetailsViewModel
    }

    val state = flowOf(
        ArticleDetailsState.Article(
            articleDetails.title,
            articleDetails.imageUrl,
            articleDetails.url,
            articleDetails.date,
            articleDetails.source,
            articleDetails.content
        )
    ).stateIn(viewModelScope, SharingStarted.Lazily, ArticleDetailsState.Loading)

    fun sendEvent(event: ArticleDetailsEvent) {
        when (event) {
            is ArticleDetailsEvent.OpenUrl -> openUrl()
        }
    }

    private fun openUrl() {
        val article = (state.value as? ArticleDetailsState.Article) ?: return

        flowCoordinator.openFlow(NavigationFlow.Browser(article.url))
    }
}