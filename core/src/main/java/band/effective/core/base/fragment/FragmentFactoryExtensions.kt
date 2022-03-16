package band.effective.core.base.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

inline fun <reified T : Fragment> createFragmentWithFactory(factory: FragmentFactory): T {
    val className = T::class.java.name
    val classLoader = T::class.java.classLoader ?: error("There is no class loader for class $className")
    return factory.instantiate(classLoader, className) as T
}
