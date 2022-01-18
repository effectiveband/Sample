package com.github.daniilbug.core.base.activity

import androidx.annotation.LayoutRes
import com.github.daniilbug.core.navigation.NavigationBinder
import javax.inject.Inject

abstract class NavigationActivity(@LayoutRes private val res: Int) : BaseActivity(res) {

    @Inject
    lateinit var navigationBinder: NavigationBinder

    abstract val containerResId: Int

    override fun onPause() {
        super.onPause()
        navigationBinder.unbind()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationBinder.bind(this)
    }
}
