package com.github.daniilbug.core.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.github.daniilbug.core.base.RootViewBinder
import com.github.daniilbug.core.navigation.flow.FlowBinder
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes private val res: Int) : AppCompatActivity() {

    @Inject
    lateinit var rootViewBinder: RootViewBinder

    @Inject
    lateinit var flowBinder: FlowBinder

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = rootViewBinder.getRootViewContainer(this)
        view = LayoutInflater.from(this).inflate(res, root, false)
        root.addView(view)
    }

    override fun onStart() {
        super.onStart()
        flowBinder.bind(this)
    }

    override fun onStop() {
        flowBinder.unbind(this)
        super.onStop()
    }
}