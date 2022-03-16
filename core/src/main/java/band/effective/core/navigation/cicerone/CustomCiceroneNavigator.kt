package band.effective.core.navigation.cicerone

import band.effective.core.base.activity.NavigationActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import timber.log.Timber

class CustomCiceroneNavigator(navigationActivity: NavigationActivity) : AppNavigator(
    navigationActivity,
    navigationActivity.containerResId
) {

    override fun errorOnApplyCommand(command: Command, error: RuntimeException) {
        Timber.e(error, "Error on applying command: $command")
    }
}