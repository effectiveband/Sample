package com.github.daniilbug.core.base

import android.app.Activity
import android.view.ViewGroup

interface RootViewBinder {
    fun getRootViewContainer(activity: Activity): ViewGroup
}