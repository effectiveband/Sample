package band.effective.core.base.activity

import androidx.annotation.LayoutRes
import band.effective.core.navigation.NavigationBinder
import javax.inject.Inject

abstract class NavigationActivity(@LayoutRes private val res: Int) : BaseActivity(res) {

    @Inject
    lateinit var navigationBinder: NavigationBinder

    abstract val containerResId: Int

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationBinder.bind(this)
    }

    override fun onPause() {
        navigationBinder.unbind()
        super.onPause()
    }
}
