package com.github.daniilbug.core.extensions

import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty

inline fun <reified T> args(
    key: String,
    crossinline otherwise: () -> T
) = ReadOnlyProperty<Fragment, T> { fragment, _ ->
    val args = fragment.arguments ?: return@ReadOnlyProperty otherwise()
    if (!args.containsKey(key)) return@ReadOnlyProperty otherwise()

    when (T::class) {
        String::class -> args.getString(key)
        Int::class -> args.getInt(key)
        Long::class -> args.getLong(key)
        else -> args.getSerializable(key)
    } as T
}

inline fun <reified T : Parcelable> parcelableArgs(
    key: String,
    crossinline otherwise: () -> T
) = ReadOnlyProperty<Fragment, T> { fragment, _ ->
    val args = fragment.arguments ?: return@ReadOnlyProperty otherwise()
    if (!args.containsKey(key)) return@ReadOnlyProperty otherwise()

    args.getParcelable(key) ?: otherwise()
}