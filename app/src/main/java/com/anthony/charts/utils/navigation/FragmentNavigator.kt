package com.anthony.charts.utils.navigation

import com.anthony.charts.ui.base.BaseFragment

interface FragmentNavigator {
    fun navigate(fragmentInstance: BaseFragment, isAddToBackStack: Boolean = false)
}