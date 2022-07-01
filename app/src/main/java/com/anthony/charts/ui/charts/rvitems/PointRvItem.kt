package com.anthony.charts.ui.charts.rvitems

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.anthony.charts.R
import com.anthony.charts.models.PointViewModel
import com.anthony.charts.ui.base.recyclerview.BaseRVItem
import com.anthony.charts.ui.base.recyclerview.BaseViewHolder
import com.anthony.charts.ui.base.recyclerview.ViewHolderCreator

data class PointRvItem(val point: PointViewModel) : BaseRVItem {
    override val itemViewType: Int = 1

    override fun getItemId(): String {
        return point.x.toString() + point.y.toString()
    }

    override fun getViewHolderCreator(): ViewHolderCreator {
        return ViewHolderCreator { parent ->
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_point, parent, false)
            PointViewHolder(view)
        }
    }

    private class PointViewHolder(view: View) : BaseViewHolder(view) {
        private val tvX = view.findViewById<TextView>(R.id.tv_point_x)
        private val tvY = view.findViewById<TextView>(R.id.tv_point_y)

        override fun bind(item: BaseRVItem) {
            item as PointRvItem
            tvX.text = item.point.x.toString()
            tvY.text = item.point.y.toString()
        }
    }
}

