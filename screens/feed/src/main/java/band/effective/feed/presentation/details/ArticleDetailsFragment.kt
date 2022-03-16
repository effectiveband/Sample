package band.effective.feed.presentation.details

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import band.effective.core.base.fragment.createFragmentWithFactory
import band.effective.core.base.viewBinding
import band.effective.core.base.viewmodel.BaseSavedStateViewModelFactory
import band.effective.core.extensions.parcelableArgs
import band.effective.coreui.extensions.load
import band.effective.feed.R
import band.effective.feed.databinding.FragmentArticleBinding
import band.effective.mainNavigation.ArticleDetails
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleDetailsFragment @Inject constructor(
    private val viewModelCreator: ArticleDetailsViewModel.Creator
) : Fragment(R.layout.fragment_article) {

    companion object {
        private const val DETAILS_KEY = "DETAILS_KEY"

        fun withDetails(
            factory: FragmentFactory,
            articleDetails: ArticleDetails
        ): ArticleDetailsFragment {
            return createFragmentWithFactory<ArticleDetailsFragment>(factory).apply {
                arguments = bundleOf(
                    DETAILS_KEY to articleDetails
                )
            }
        }
    }

    private val articleDetails: ArticleDetails by parcelableArgs(
        DETAILS_KEY,
        otherwise = { error("The fragment must be created with ${::withDetails.name}") }
    )

    private val viewModel: ArticleDetailsViewModel by viewModels {
        BaseSavedStateViewModelFactory(this) {
            viewModelCreator.create(articleDetails)
        }
    }

    private val binding by viewBinding { FragmentArticleBinding.bind(it.requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            articleOpenWebButton.setOnClickListener {
                viewModel.sendEvent(ArticleDetailsEvent.OpenUrl)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest(::setState)
            }
        }
    }

    private fun setState(state: ArticleDetailsState) {
        when (state) {
            ArticleDetailsState.Loading -> setLoading()
            is ArticleDetailsState.Article -> setArticle(state)
        }
    }

    private fun setLoading() {
        with(binding) {
            articleScrollView.isVisible = false
            articleProgressBar.isVisible = true
        }
    }

    private fun setArticle(state: ArticleDetailsState.Article) {
        with(binding) {
            articleScrollView.isVisible = true
            articleProgressBar.isVisible = false
            articleImage.load(state.imageUrl)
            articleTitle.text = state.title
            articleContentText.text = state.content
            val relativeDateString = DateUtils.getRelativeTimeSpanString(
                state.date.time,
                System.currentTimeMillis(),
                DateUtils.DAY_IN_MILLIS
            )
            articleDateText.text = relativeDateString
            articleSourceText.text = state.source
        }
    }
}