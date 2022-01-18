package band.effective.core.base.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Provider

class AppFragmentFactory(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = creators[fragmentClass] ?: creators.entries.firstOrNull {
            fragmentClass.isAssignableFrom(it.key)
        }?.value ?: return super.instantiate(classLoader, className)

        return try {
            creator.get()
        } catch (e: Exception) {
            super.instantiate(classLoader, className)
        }
    }
}
