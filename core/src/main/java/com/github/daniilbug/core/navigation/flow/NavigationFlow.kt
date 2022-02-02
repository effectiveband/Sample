package com.github.daniilbug.core.navigation.flow

sealed class NavigationFlow {
    object Main : NavigationFlow()
    class Browser(val url: String): NavigationFlow()
}
