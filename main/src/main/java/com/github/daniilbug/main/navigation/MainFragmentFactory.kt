package com.github.daniilbug.main.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.about.di.aboutComponent
import com.github.daniilbug.feed.di.feedComponent

class MainFragmentFactory(activity: Activity): FragmentFactory() {

    private val factories = listOf(
        feedComponent.getInstance(activity).getFragmentFactory(),
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