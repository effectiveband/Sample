package com.github.daniilbug.core.navigation.flow

interface FlowCoordinator {
    fun replaceFlow(navigationFlow: NavigationFlow, deeplink: String? = null)
    fun openFlow(navigationFlow: NavigationFlow, deeplink: String? = null)
    fun finishFlow()
}
