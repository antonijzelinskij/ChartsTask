package com.anthony.charts.ui.charts

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anthony.charts.R
import com.anthony.charts.models.PointGroupViewModel
import com.anthony.charts.models.PointViewModel
import com.anthony.charts.ui.base.BaseFragment
import com.anthony.charts.ui.base.recyclerview.BaseListAdapter
import com.anthony.charts.ui.charts.rvitems.PointRvItem
import com.anthony.charts.utils.ActivityResultPermission
import com.anthony.charts.utils.delegates.parcelableArgument
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import moxy.ktx.moxyPresenter


class ChartFragment() : BaseFragment(R.layout.fragment_chart),
    ChartViewState, View.OnClickListener {

    override val fragmentTag: String = "ChartsFragment"

    private val presenter by moxyPresenter { ChartsPresenter() }

    private lateinit var rvPointsTable: RecyclerView
    private lateinit var lcChart: LineChart
    private lateinit var ivDownload: ImageView

    private val adapter = BaseListAdapter()

    private var pointGroup: PointGroupViewModel by parcelableArgument(POINT_GROUP_EXTRA)

    private val activityResultPermission = ActivityResultPermission(this)

    constructor(pointGroup: PointGroupViewModel) : this() {
        this.pointGroup = pointGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPointsTable = view.findViewById(R.id.rv_points_table)
        lcChart = view.findViewById(R.id.lc_chart)
        ivDownload = view.findViewById(R.id.iv_download_chart)

        rvPointsTable.adapter = adapter
        rvPointsTable.addItemDecoration(
            PointsItemDecoration(
                requireContext(),
                R.color.color_divider
            )
        )

        lcChart.description.isEnabled = false
        lcChart.setTouchEnabled(true)
        lcChart.setBackgroundColor(Color.WHITE)
        lcChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lcChart.axisRight.isEnabled = false
        lcChart.isSaveEnabled = true

        val sortedPoints = pointGroup.getSortedPointsByX()
        setupTable(sortedPoints)
        setupChart(sortedPoints)

        ivDownload.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == ivDownload.id) {
            activityResultPermission
                .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onPermissionGranted { saveChartToGallery() }
                .ask()
        }
    }

    private fun setupTable(sortedPoints: List<PointViewModel>) {
        val rvPoints = sortedPoints.map { PointRvItem(it) }
        adapter.update(rvPoints)
    }

    private fun setupChart(sortedPoints: List<PointViewModel>) {
        val lineColor = ContextCompat.getColor(requireContext(), R.color.chart_line)
        val chartValues = sortedPoints.map {
            Entry(it.x.toFloat(), it.y.toFloat())
        }

        val lineDataSet = LineDataSet(chartValues, "Some points").apply {
            setDrawValues(false)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            lineWidth = 3f
            circleRadius = 4f
            setCircleColor(lineColor)
            circleHoleColor = lineColor
            color = lineColor
        }

        val lineData = LineData(lineDataSet)
        lcChart.data = lineData
        lcChart.invalidate()
    }

    private fun saveChartToGallery() {
        val result = lcChart.saveToGallery("chart")
        if (result) {
            Toast.makeText(
                requireContext(),
                R.string.chart_fragment_successfull_saving,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        private const val POINT_GROUP_EXTRA = "POINT_GROUP_EXTRA"
    }
}