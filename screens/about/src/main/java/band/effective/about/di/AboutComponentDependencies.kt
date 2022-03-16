package band.effective.about.di

import band.effective.core.di.CommonDependencies
import band.effective.core.di.InjectedKey
import band.effective.core.navigation.flow.FlowCoordinator
import javax.inject.Named

interface AboutComponentDependencies : CommonDependencies {
    @Named(InjectedKey.Configuration.VERSION_NAME)
    fun getAppVersionName(): String
    fun getFlowCoordinator(): FlowCoordinator
}