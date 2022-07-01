package com.anthony.charts.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.anthony.charts.R
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment

abstract class BaseActivity(@LayoutRes layout: Int) : MvpAppCompatActivity(layout), BaseViewState {

    private var alertDialog: AlertDialog? = null

    override fun onError(stringRes: Int) {
        if (alertDialog?.isShowing == true) {
            alertDialog?.cancel()
        }

        alertDialog = AlertDialog.Builder(this)
            .setPositiveButton(R.string.chart_settings_fragment_error_ok_btn, null)
            .setTitle(R.string.chart_settings_fragment_error_title)
            .setMessage(stringRes)
            .create()

        alertDialog?.show()
    }
}