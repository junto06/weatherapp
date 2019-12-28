package com.weatherapp.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.weatherapp.data.model.City

class HomeDataDiffCallback(private val oldList:List<City>,private val newList:List<City>):
    DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name
    }

    override fun getOldListSize(): Int =  oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }

}