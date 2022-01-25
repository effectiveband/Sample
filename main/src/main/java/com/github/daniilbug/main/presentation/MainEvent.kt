package com.github.daniilbug.main.presentation

sealed class MainEvent {
    object OpenFeed: MainEvent()
    object OpenAbout: MainEvent()
}