package com.github.daniilbug.about.di

import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.core.di.InjectedKey
import com.github.daniilbug.core.navigation.flow.FlowCoordinator
import javax.inject.Named

interface AboutComponentDependencies : CommonDependencies {
    @Named(InjectedKey.Configuration.VERSION_NAME)
    fun getAppVersionName(): String
    fun getFlowCoordinator(): FlowCoordinator
}