package band.effective.headlines.navigation

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import band.effective.core.navigation.flow.FlowBinder
import band.effective.core.navigation.flow.FlowCoordinator
import band.effective.core.navigation.flow.NavigationFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityFlowCoordinator @Inject constructor(
    private val mapper: NavigationFlowMapper
) : FlowCoordinator {
    private var activity: AppCompatActivity? = null

    inner class Binder : FlowBinder {
        override fun bind(activity: AppCompatActivity) {
            this@ActivityFlowCoordinator.activity = activity
        }

        override fun unbind(activity: AppCompatActivity) {
            if (this@ActivityFlowCoordinator.activity === activity) {
                this@ActivityFlowCoordinator.activity = null
            }
        }
    }

    override fun replaceFlow(navigationFlow: NavigationFlow, deeplink: String?) {
        val previousActivity = activity
        openFlow(navigationFlow, deeplink)
        previousActivity?.finish()
    }

    override fun openFlow(navigationFlow: NavigationFlow, deeplink: String?) {
        activity?.let {
            val intent = mapper.map(it, navigationFlow)
            if (deeplink != null) intent.data = Uri.parse(deeplink)
            it.startActivity(intent)
        }
    }

    override fun finishFlow() {
        activity?.finish()
        activity = null
    }
}
