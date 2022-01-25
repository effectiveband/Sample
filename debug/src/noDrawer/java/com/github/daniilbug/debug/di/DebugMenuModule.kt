package com.github.daniilbug.debug.di

import com.github.daniilbug.core.base.RootViewBinder
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DebugMenuModule {

    @Binds
    @Singleton
    fun bindRootViewBinder(
        rootViewBinder: NoDebugDrawerRootViewBinder
    ): RootViewBinder
}