package band.effective.main.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import band.effective.about.di.aboutComponent
import band.effective.feed.di.feedComponent
import band.effective.search.di.searchComponent

class MainFragmentFactory(activity: Activity): FragmentFactory() {

    private val factories = listOf(
        feedComponent.getInstance(activity).getFragmentFactory(),
        searchComponent.getInstance(activity).getFragmentFactory(),
        aboutComponent.getInstance(activity).getFragmentFactory()
    )

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        for (factory in factories) {
            try {
                return factory.instantiate(classLoader, className)
            } catch (e: Exception) {
                continue
            }
        }
        error("There is no factory for fragment $className")
    }
}