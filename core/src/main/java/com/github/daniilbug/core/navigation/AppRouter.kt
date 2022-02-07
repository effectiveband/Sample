package com.github.daniilbug.core.navigation

interface AppRouter<AppScreenClass : AppScreen> {
    fun open(appScreen: AppScreenClass)
    fun openDeepLink(uri: String, addToBackStack: Boolean = false)
    fun newRoot(appScreen: AppScreenClass)
    fun newChainRoot(vararg screens: AppScreenClass)
    fun replace(appScreen: AppScreenClass)
    fun back()
    fun backTo(appScreen: AppScreenClass)
}