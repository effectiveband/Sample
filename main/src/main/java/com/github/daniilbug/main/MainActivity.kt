package com.github.daniilbug.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.daniilbug.core.base.activity.BaseActivity
import com.github.daniilbug.main.di.mainComponent
import com.github.daniilbug.main.navigation.MainFragmentFactory
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent.getInstance(this).inject(this)
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = MainFragmentFactory(activity = this)
        viewModel
    }
}