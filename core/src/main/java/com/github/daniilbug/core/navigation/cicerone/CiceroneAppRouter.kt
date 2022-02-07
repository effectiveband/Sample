package com.github.daniilbug.core.navigation.cicerone

import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.core.navigation.AppScreen
import com.github.terrakok.cicerone.Router

class CiceroneAppRouter<AppScreenClass : AppScreen>(
    private val router: Router,
    private val mapper: CiceroneScreenMapper<AppScreenClass>
) : AppRouter<AppScreenClass> {

    override fun open(appScreen: AppScreenClass) {
        router.navigateTo(mapper.map(appScreen))
    }

    override fun newRoot(appScreen: AppScreenClass) {
        router.newRootScreen(mapper.map(appScreen))
    }

    override fun newChainRoot(vararg screens: AppScreenClass) {
        router.newRootChain(*screens.map(mapper::map).toTypedArray())
    }

    override fun replace(appScreen: AppScreenClass) {
        router.replaceScreen(mapper.map(appScreen))
    }

    override fun back() {
        router.exit()
    }

    override fun backTo(appScreen: AppScreenClass) {
        router.backTo(mapper.map(appScreen))
    }

    override fun openDeepLink(uri: String, addToBackStack: Boolean) {
        val screens = mapper.mapUri(uri).map(mapper::map).toTypedArray()
        if (addToBackStack) {
            router.newChain(*screens)
        } else {
            router.newRootChain(*screens)
        }
    }
}
