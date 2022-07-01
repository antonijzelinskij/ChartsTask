package com.anthony.charts.ui.main

import android.os.Bundle
import com.anthony.charts.R
import com.anthony.charts.ui.base.BaseActivity
import com.anthony.charts.ui.base.BaseFragment
import com.anthony.charts.ui.chartsettings.ChartSettingsFragment
import com.anthony.charts.utils.navigation.FragmentNavigator
import moxy.ktx.moxyPresenter

class MainActivity : BaseActivity(R.layout.activity_main), MainViewState, FragmentNavigator {

    private val presenter by moxyPresenter { MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateHomeIcon()

        supportFragmentManager.addOnBackStackChangedListener {
            updateHomeIcon()
        }
    }

    override fun openChartSettings() {
        navigate(ChartSettingsFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun navigate(fragmentInstance: BaseFragment, isAddToBackStack: Boolean) {
        supportFragmentManager.apply {
            val fragmentTransaction = beginTransaction()

            var fragment = findFragmentByTag(fragmentInstance.fragmentTag)
            if (fragment == null) {
                fragment = fragmentInstance
                fragmentTransaction.add(
                    R.id.fl_fragment_container,
                    fragment,
                    fragmentInstance.fragmentTag
                )
            } else {
                fragmentTransaction.attach(fragment)
            }

            val currentFrag = primaryNavigationFragment
            if (fragment != currentFrag) {
                if (currentFrag != null) {
                    fragmentTransaction.detach(currentFrag)
                }

                if (isAddToBackStack) {
                    fragmentTransaction.addToBackStack(fragmentInstance.fragmentTag)
                }

                fragmentTransaction
                    .setReorderingAllowed(true)
                    .setPrimaryNavigationFragment(fragment)
                    .commit()
            }
        }
    }

    private fun updateHomeIcon() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_24_close)
        } else {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_24_arrow_back)
        }
    }
}