package com.github.daniilbug.main.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.daniilbug.core.base.activity.NavigationActivity
import com.github.daniilbug.main.R
import com.github.daniilbug.main.di.mainComponent
import com.github.daniilbug.main.navigation.MainFragmentFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.DynamicColors
import javax.inject.Inject

class MainActivity : NavigationActivity(R.layout.activity_main) {

    override val containerResId: Int
        get() = R.id.mainFragmentContainer

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.getInstance(this).inject(this)
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(activity = this)
        findViewById<BottomNavigationView>(R.id.mainBottomNavigation).setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mainMenuNewsItem -> viewModel.sendEvent(MainEvent.OpenFeed)
                R.id.mainMenuAboutItem -> viewModel.sendEvent(MainEvent.OpenAbout)
                else -> return@setOnItemSelectedListener false
            }
            true
        }
        viewModel
    }
}