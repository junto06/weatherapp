package com.weatherapp.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.data.model.City
import com.weatherapp.databinding.HomeListItemBinding

class HomeViewItem(private val itemBinding:HomeListItemBinding):RecyclerView.ViewHolder(itemBinding.root){

    fun bindCity(city:City){
        itemBinding.city = city
    }
}