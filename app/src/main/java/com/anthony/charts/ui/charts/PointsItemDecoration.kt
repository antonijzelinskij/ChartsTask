package com.anthony.charts.ui.charts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.anthony.charts.R

class PointsItemDecoration(
    private val context: Context,
    @ColorRes private val dividerColorRes: Int
) : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, dividerColorRes)
        strokeWidth = context.resources.getDimension(R.dimen.divider_width)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.drawHorizontalLine(parent, (parent.height / 2).toFloat())
        canvas.drawHorizontalLine(parent, parent.top.toFloat() + paint.strokeWidth / 2)
        canvas.drawHorizontalLine(parent, parent.bottom.toFloat() - paint.strokeWidth / 2)

        parent.children.forEach { view ->
            val childAdapterPosition = parent.getChildAdapterPosition(view)
            if (childAdapterPosition == 0) {
                canvas.drawVerticalLine(parent, view.left.toFloat() + paint.strokeWidth / 2)
            }
            canvas.drawVerticalLine(parent, view.right.toFloat())
        }
    }

    private fun Canvas.drawHorizontalLine(view: View, y: Float) {
        drawLine(
            0f,
            y,
            view.width.toFloat(),
            y,
            paint
        )
    }

    private fun Canvas.drawVerticalLine(view: View, x: Float) {
        drawLine(
            x,
            0f,
            x,
            view.height.toFloat(),
            paint
        )
    }
}