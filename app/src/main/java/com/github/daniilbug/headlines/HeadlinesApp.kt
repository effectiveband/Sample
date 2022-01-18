package com.github.daniilbug.headlines

import android.app.Application
import com.github.daniilbug.headlines.di.AppComponent
import com.github.daniilbug.headlines.di.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone

class HeadlinesApp: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        val cicerone = Cicerone.create()

        component = DaggerAppComponent.builder()
            .applicationContext(this)
            .cicerone(cicerone)
            .build()
    }
}