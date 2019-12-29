package com.weatherapp.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.weatherapp.R
import com.weatherapp.databinding.CityDetailsFragmentBinding
import com.weatherapp.util.EventHandler
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WeatherDetailsFragment:Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var weatherDetailsViewModel: WeatherDetailsViewModel

    private lateinit var dataBinding: CityDetailsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        weatherDetailsViewModel = ViewModelProviders.of(this,viewModelFactory).
            get(WeatherDetailsViewModel::class.java)

        val city = WeatherDetailsFragmentArgs.fromBundle(requireNotNull(arguments)).cityItem

        weatherDetailsViewModel.loadWeather(city)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupErrorHandler()

        setupWeatherInfo()
    }

    private fun setupWeatherInfo() {
        weatherDetailsViewModel.data.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupErrorHandler() {
        weatherDetailsViewModel.error.observe(viewLifecycleOwner,EventHandler{
            var error = getString(it)

            Snackbar.make(dataBinding.root,error, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.city_details_fragment,container,false)

        dataBinding = CityDetailsFragmentBinding.bind(root).apply {
            detailsViewModel = weatherDetailsViewModel
        }

        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }
}