package com.github.daniilbug.core.navigation.flow

import androidx.appcompat.app.AppCompatActivity

interface FlowBinder {
    fun bind(activity: AppCompatActivity)
    fun unbind(activity: AppCompatActivity)
}
