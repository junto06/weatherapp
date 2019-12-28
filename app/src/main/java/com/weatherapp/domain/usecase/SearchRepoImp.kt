package com.weatherapp.domain.usecase

import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.DataSource
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepoImp @Inject constructor(private val dataSource: DataSource):SearchRepo{

    override fun searchCity(cityName: String): Observable<SearchResult> {
        return dataSource.searchCity(cityName)
            .filter { result ->
                if(!result.hasError){
                    val searchBy = cityName.toLowerCase()
                    val filtered = result.cityList.filter { city ->
                        var cityName = city.name.toLowerCase()
                        cityName.startsWith(searchBy) || cityName.contains(searchBy)
                    }
                    result.cityList = filtered
                    true
                }else{
                    false
                }
        }
    }
}