package com.anthony.charts.ui.base.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BaseListAdapter : ListAdapter<BaseRVItem, BaseViewHolder>(DiffUtilCallback()) {

    private val viewHolderCreators: MutableMap<Int, ViewHolderCreator> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolderCreator = viewHolderCreators[viewType] ?: throw NullPointerException()
        return viewHolderCreator.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemViewType
    }

    fun update(list: List<BaseRVItem>) {
        this.submitList(ArrayList(list))
        updateViewHolders(list)
    }

    private fun updateViewHolders(list: List<BaseRVItem>) {
        viewHolderCreators.clear()
        list.forEach {
            val alreadyContains = viewHolderCreators.containsKey(it.itemViewType)
            if (!alreadyContains) {
                viewHolderCreators[it.itemViewType] = it.getViewHolderCreator()
            }
        }
    }
}

open class DiffUtilCallback<D : BaseRVItem> : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem.getItemId() == newItem.getItemId()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem == newItem
    }
}