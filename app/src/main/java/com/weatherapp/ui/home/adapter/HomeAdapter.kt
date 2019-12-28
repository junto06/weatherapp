package com.weatherapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.databinding.HomeListItemBinding

open class HomeAdapter(private val adapterAction: HomeAdapterAction): RecyclerView.Adapter<HomeViewItem>(){

    private var cityData = mutableListOf<City>()

    fun setCityData(newData:List<City>){
        val result = DiffUtil.calculateDiff(HomeDataDiffCallback(cityData, newData))
        cityData.clear()
        cityData.addAll(newData)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int  = cityData.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewItem {

        val inflater =  LayoutInflater.from(parent.context)

        val viewBinding = DataBindingUtil.inflate<HomeListItemBinding>(inflater,
            R.layout.home_list_item,parent,false)

        viewBinding.adapterAction = adapterAction

        return HomeViewItem(viewBinding)
    }

    override fun onBindViewHolder(holder: HomeViewItem, position: Int) {
        holder.bindCity(cityData[position])
    }

}