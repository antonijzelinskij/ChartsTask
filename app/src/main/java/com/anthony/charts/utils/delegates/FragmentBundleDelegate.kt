package com.anthony.charts.utils.delegates

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class FragmentBundleDelegate<V>(private val fragment: Fragment, private val key: String) :
    ReadWriteProperty<Fragment, V> {

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: V) {
        val arguments = getArguments()
        return when (value) {
            is Parcelable -> arguments.putParcelable(key, value)
            else -> throw NoSuchElementException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        return getArguments().get(key) as V
    }

    private fun getArguments(): Bundle {
        if (fragment.arguments == null) {
            fragment.arguments = Bundle()
        }
        return fragment.arguments!!
    }
}

internal inline fun <reified T> Fragment.parcelableArgument(
    key: String
): ReadWriteProperty<Fragment, T> {
    return FragmentBundleDelegate(this, key)
}