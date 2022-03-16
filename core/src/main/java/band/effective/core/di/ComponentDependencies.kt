package band.effective.core.di

import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment

interface ComponentDependencies

interface CommonDependencies : ComponentDependencies {
    fun getContext(): Context
}

typealias ComponentDependenciesProvider =
        Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

fun Fragment.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    var current: Fragment? = parentFragment
    while (current !is HasComponentDependencies?) {
        current = current?.parentFragment
    }

    val hasDaggerProviders = current ?: when (activity?.application) {
        is HasComponentDependencies -> activity?.application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Activity.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (application) {
        is HasComponentDependencies -> application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

fun Service.findComponentDependenciesProvider(): ComponentDependenciesProvider {
    val hasDaggerProviders = when (application) {
        is HasComponentDependencies -> application as HasComponentDependencies
        else -> throw IllegalStateException("Can not find suitable dagger provider for $this")
    }
    return hasDaggerProviders.dependencies
}

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}

inline fun <reified T : ComponentDependencies> Service.findComponentDependencies(): T {
    return findComponentDependenciesProvider()[T::class.java] as T
}