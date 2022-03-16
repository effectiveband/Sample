package band.effective.main.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import band.effective.core.base.activity.NavigationActivity
import band.effective.main.R
import band.effective.main.di.mainComponent
import band.effective.main.navigation.MainFragmentFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : NavigationActivity(R.layout.activity_main) {

    override val containerResId: Int
        get() = R.id.mainFragmentContainer

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        mainComponent.getInstance(this).inject(this)
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(activity = this)
        findViewById<BottomNavigationView>(R.id.mainBottomNavigation).setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mainMenuNewsItem -> viewModel.sendEvent(MainEvent.OpenFeed)
                R.id.mainMenuAboutItem -> viewModel.sendEvent(MainEvent.OpenAbout)
                R.id.mainMenuSearchItem -> viewModel.sendEvent(MainEvent.OpenSearch)
                else -> return@setOnItemSelectedListener false
            }
            true
        }
        viewModel
    }
}