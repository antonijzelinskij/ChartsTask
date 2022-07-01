package com.anthony.charts.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.anthony.charts.utils.navigation.FragmentNavigator
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes layout: Int) : MvpAppCompatFragment(layout), BaseViewState {
    abstract val fragmentTag: String

    protected lateinit var baseActivity: BaseActivity
        private set
    protected lateinit var navigator: FragmentNavigator
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
        navigator = activity as FragmentNavigator
    }

    override fun onError(stringRes: Int) {
        baseActivity.onError(stringRes)
    }
}