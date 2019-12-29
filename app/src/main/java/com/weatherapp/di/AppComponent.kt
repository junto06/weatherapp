package com.weatherapp.di

import android.app.Application
import com.weatherapp.App
import com.weatherapp.data.di.RepoModule
import com.weatherapp.data.repo.remote.di.NetworkModule
import com.weatherapp.data.repo.remote.di.RemoteModule
import com.weatherapp.data.storage.CityDao
import com.weatherapp.data.storage.DaoProvider
import com.weatherapp.domain.di.UseCaseModule
import com.weatherapp.ui.base.FragmentModule
import com.weatherapp.ui.base.ViewModelModule
import com.weatherapp.util.android.imageloading.di.ImageLoadingModule
import com.weatherapp.util.scheduler.SchedulerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,NetworkModule::class, RepoModule::class,
    UseCaseModule::class, SchedulerModule::class,ViewModelModule::class, FragmentModule::class,
    AndroidInjectionModule::class, ImageLoadingModule::class,DaoProvider::class])
interface AppComponent{
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }

    fun inject(app: App)
}