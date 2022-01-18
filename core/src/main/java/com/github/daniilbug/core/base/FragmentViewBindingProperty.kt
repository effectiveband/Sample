package com.github.daniilbug.core.base

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingProperty<T : ViewBinding>(
    private val viewBinder: (Fragment) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var viewBinding: T? = null
    private val lifecycleObserver = BindingLifecycleObserver()

    @MainThread
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        this.viewBinding?.let { return it }

        thisRef.viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        return viewBinder(thisRef).also { vb -> this.viewBinding = vb }
    }

    private inner class BindingLifecycleObserver : LifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            // Fragment.viewLifecycleOwner call LifecycleObserver.onDestroy() before Fragment.onDestroyView().
            // That's why we need to postpone reset of the viewBinding
            mainHandler.post {
                viewBinding = null
            }
        }
    }
}

@Suppress("unused")
inline fun <reified T : ViewBinding> Fragment.viewBinding(noinline viewBinder: (Fragment) -> T): ReadOnlyProperty<Fragment, T> {
    return FragmentViewBindingProperty(viewBinder)
}
