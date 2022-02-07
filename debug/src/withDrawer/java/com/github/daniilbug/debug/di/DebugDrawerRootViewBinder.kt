package com.github.daniilbug.debug.di

import android.app.Activity
import android.view.ViewGroup
import au.com.gridstone.debugdrawer.DebugDrawer
import au.com.gridstone.debugdrawer.DeviceInfoModule
import au.com.gridstone.debugdrawer.okhttplogs.HttpLogger
import au.com.gridstone.debugdrawer.okhttplogs.OkHttpLoggerModule
import au.com.gridstone.debugdrawer.timber.TimberModule
import com.github.daniilbug.core.base.RootViewBinder
import javax.inject.Inject

class DebugDrawerRootViewBinder @Inject constructor(
    private val httpLogger: HttpLogger
): RootViewBinder {

    override fun getRootViewContainer(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
            .addSectionTitle("Logs")
            .addModule(OkHttpLoggerModule(httpLogger))
            .addModule(TimberModule())
            .addSectionTitle("Device Info")
            .addModule(DeviceInfoModule())
            .buildMainContainer()
    }
}