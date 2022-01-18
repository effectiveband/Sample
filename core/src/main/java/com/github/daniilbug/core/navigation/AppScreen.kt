package com.github.daniilbug.core.navigation

interface AppScreen {
    val name: String
}

val AppScreen.tag: String get() = javaClass.name