package band.effective.debug.di

import android.app.Activity
import android.view.ViewGroup
import band.effective.core.base.RootViewBinder
import javax.inject.Inject

class NoDebugDrawerRootViewBinder @Inject constructor(): RootViewBinder {
    override fun getRootViewContainer(activity: Activity): ViewGroup {
        return activity.findViewById(android.R.id.content) as ViewGroup
    }
}