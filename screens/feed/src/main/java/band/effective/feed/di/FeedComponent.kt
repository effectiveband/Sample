package band.effective.feed.di

import android.app.Activity
import androidx.fragment.app.FragmentFactory
import band.effective.core.di.featureComponent
import band.effective.core.di.findComponentDependencies
import dagger.Component

val feedComponent = featureComponent<FeedComponent, Activity> { activity ->
    DaggerFeedComponent.builder()
        .feedComponentDependencies(activity.findComponentDependencies())
        .build()
}

@Component(
    dependencies = [
        FeedComponentDependencies::class
    ],
    modules = [
        FeedFragmentModule::class,
        FeedViewModelModule::class
    ]
)
interface FeedComponent {
    fun getFragmentFactory(): FragmentFactory
}