package band.effective.core.di

import com.github.daniilbug.core.di.ComponentDependenciesProvider

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}