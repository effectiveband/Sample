package com.github.daniilbug.about.presentation

import androidx.lifecycle.ViewModel
import com.github.daniilbug.core.navigation.flow.FlowCoordinator
import com.github.daniilbug.core.navigation.flow.NavigationFlow
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val flowCoordinator: FlowCoordinator
): ViewModel() {

    fun sendEvent(event: AboutEvent) {
        when (event) {
            AboutEvent.OpenApi -> openApi()
        }
    }

    private fun openApi() {
        flowCoordinator.openFlow(NavigationFlow.Browser("https://newsapi.org/"))
    }
}