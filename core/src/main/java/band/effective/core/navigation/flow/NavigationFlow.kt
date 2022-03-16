package band.effective.core.navigation.flow

sealed class NavigationFlow {
    object Main : NavigationFlow()
    class Browser(val url: String): NavigationFlow()
}
