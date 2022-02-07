package com.github.daniilbug.headlines.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.daniilbug.core.navigation.flow.NavigationFlow
import com.github.daniilbug.main.presentation.MainActivity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class NavigationFlowMapper @Inject constructor() {
    fun map(context: Context, navigationFlow: NavigationFlow): Intent {
        return when (navigationFlow) {
            is NavigationFlow.Main -> Intent(context, MainActivity::class.java)
            is NavigationFlow.Browser -> Intent(Intent.ACTION_VIEW, Uri.parse(navigationFlow.url))
        }
    }
}
