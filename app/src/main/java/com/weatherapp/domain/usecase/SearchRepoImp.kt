package com.weatherapp.domain.usecase

import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.CityDataSource
import io.reactivex.Observable

class SearchRepoImp(private val cityDataSource: CityDataSource):SearchRepo{

    override fun searchCity(cityName: String): Observable<SearchResult> {
        return cityDataSource.searchCity(cityName)
            .filter { result ->
                if(!result.hasError){
                    val filtered = result.cityList.filter { city ->
                        city.name.startsWith(cityName)
                    }
                    result.cityList = filtered
                    true
                }else{
                    false
                }
        }
    }
}