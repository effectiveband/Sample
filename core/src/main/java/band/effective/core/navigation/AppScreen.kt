package band.effective.core.navigation

interface AppScreen {
    val name: String
}

val AppScreen.key: String get() = javaClass.name