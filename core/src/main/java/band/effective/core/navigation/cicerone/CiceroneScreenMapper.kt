package band.effective.core.navigation.cicerone

import band.effective.core.navigation.AppScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface CiceroneScreenMapper<AppScreenClass : AppScreen> {
    fun map(screen: AppScreenClass): FragmentScreen
    fun mapUri(uriString: String): List<AppScreenClass>
}
