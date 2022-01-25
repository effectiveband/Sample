package com.github.daniilbug.debug.di

import android.app.Activity
import android.view.ViewGroup
import au.com.gridstone.debugdrawer.DebugDrawer
import au.com.gridstone.debugdrawer.DeviceInfoModule
import au.com.gridstone.debugdrawer.timber.TimberModule
import com.github.daniilbug.core.base.RootViewBinder
import javax.inject.Inject

class DebugDrawerRootViewBinder @Inject constructor(): RootViewBinder {

    override fun getRootViewContainer(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
            .addSectionTitle("Logs")
            .addModule(TimberModule())
            .addSectionTitle("Device Info")
            .addModule(DeviceInfoModule())
            .buildMainContainer()
    }
}