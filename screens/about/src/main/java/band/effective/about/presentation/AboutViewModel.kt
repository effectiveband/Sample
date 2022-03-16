package band.effective.about.presentation

import androidx.lifecycle.ViewModel
import band.effective.core.navigation.flow.FlowCoordinator
import band.effective.core.navigation.flow.NavigationFlow
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