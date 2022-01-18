package com.github.daniilbug.core.navigation

import com.github.daniilbug.core.base.activity.NavigationActivity

interface NavigationBinder {
    fun bind(navigationActivity: NavigationActivity)
    fun unbind()
}
