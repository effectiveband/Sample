package com.github.daniilbug.about.presentation

sealed class AboutEvent {
    object OpenApi: AboutEvent()
}