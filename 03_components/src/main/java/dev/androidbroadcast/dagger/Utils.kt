package dev.androidbroadcast.dagger

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import retrofit2.Retrofit
import kotlin.properties.ReadOnlyProperty

fun stringArgs(key: String): ReadOnlyProperty<Fragment, String> {
    return ReadOnlyProperty { thisRef, _ ->
        val args = thisRef.requireArguments()
        require(args.containsKey(key)) { "Arguments don't contain key '$key'" }
        requireNotNull(args.getString(key))
    }
}

fun FragmentManager.isFragmentContainerEmpty(@IdRes id: Int): Boolean {
    return findFragmentById(id) == null
}
