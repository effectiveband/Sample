package com.github.daniilbug.debug.di

import android.app.Activity
import android.view.ViewGroup
import au.com.gridstone.debugdrawer.DebugDrawer
import com.github.daniilbug.core.base.RootViewBinder
import javax.inject.Inject

class DebugDrawerRootViewBinder @Inject constructor(): RootViewBinder {

    override fun getRootViewContainer(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
            .buildMainContainer()
    }
}