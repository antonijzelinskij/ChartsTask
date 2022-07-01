package com.anthony.charts.ui.base.recyclerview

interface BaseRVItem {
    val itemViewType: Int

    fun getItemId(): String

    fun getViewHolderCreator(): ViewHolderCreator
}
