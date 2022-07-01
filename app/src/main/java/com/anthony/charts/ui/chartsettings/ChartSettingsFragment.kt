package com.anthony.charts.ui.chartsettings

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.anthony.charts.R
import com.anthony.charts.models.PointGroupViewModel
import com.anthony.charts.ui.base.BaseFragment
import com.anthony.charts.ui.charts.ChartFragment
import moxy.ktx.moxyPresenter


class ChartSettingsFragment : BaseFragment(R.layout.fragment_chart_settings),
    ChartSettingsViewState,
    View.OnClickListener {

    override val fragmentTag: String = "ChartSettingsFragment"

    private val presenter by moxyPresenter { ChartSettingsPresenter() }

    private lateinit var etPoints: EditText
    private lateinit var pbProgress: ProgressBar
    private lateinit var btnApplyPoints: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etPoints = view.findViewById(R.id.et_points)
        pbProgress = view.findViewById(R.id.lpb_progress_bar)
        btnApplyPoints = view.findViewById(R.id.btn_apply_points)

        btnApplyPoints.setOnClickListener(this)
    }

    override fun onPointsLoad(pointGroup: PointGroupViewModel) {
        navigator.navigate(ChartFragment(pointGroup), true)
    }

    override fun onStartLoading() {
        pbProgress.isVisible = true
    }

    override fun onStopLoading() {
        pbProgress.isGone = true
    }

    override fun onClick(view: View) {
        when (view.id) {
            btnApplyPoints.id -> applyPoints()
        }
    }

    private fun applyPoints() {
        val pointsText = etPoints.text.toString()
        if (pointsText.isBlank()) {
            onError(R.string.chart_settings_fragment_error_empty_field)
            return
        }

        try {
            val pointCount = pointsText.toInt()
            presenter.loadPoints(pointCount)
        } catch (e: NumberFormatException) {
            onError(R.string.chart_settings_fragment_error_no_letters)
        }
    }
}