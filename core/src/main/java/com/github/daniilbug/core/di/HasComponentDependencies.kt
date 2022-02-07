package com.github.daniilbug.core.di

interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}