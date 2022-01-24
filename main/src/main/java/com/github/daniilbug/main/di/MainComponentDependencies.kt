package com.github.daniilbug.main.di

import com.github.daniilbug.core.base.RootViewBinder
import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.core.navigation.flow.FlowBinder
import com.github.daniilbug.mainNavigation.MainScreen

interface MainComponentDependencies : CommonDependencies {
    fun getRootViewBinder(): RootViewBinder
    fun getFlowBinder(): FlowBinder
    fun getMainRouter(): AppRouter<MainScreen>
}