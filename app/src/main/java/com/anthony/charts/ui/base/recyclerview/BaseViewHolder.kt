package com.anthony.charts.ui.base.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: BaseRVItem) {}
}

fun interface ViewHolderCreator {
    fun createViewHolder(parent: ViewGroup): BaseViewHolder
}