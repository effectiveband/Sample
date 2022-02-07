package com.github.daniilbug.core.navigation.cicerone

import com.github.daniilbug.core.navigation.AppScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface CiceroneScreenMapper<AppScreenClass : AppScreen> {
    fun map(screen: AppScreenClass): FragmentScreen
    fun mapUri(uriString: String): List<AppScreenClass>
}
