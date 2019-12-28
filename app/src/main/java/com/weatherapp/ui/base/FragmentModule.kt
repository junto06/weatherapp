package com.weatherapp.ui.base

import com.weatherapp.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}