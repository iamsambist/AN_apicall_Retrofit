package com.sunaa.apifetch.fetchdata.data.repo

import com.sunaa.apifetch.fetchdata.data.remote.ApiService
import com.sunaa.apifetch.fetchdata.data.remote.JsonModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getData() : Response<List<JsonModel>> {
        return apiService.getData()
    }

    suspend fun getData(id : Int) : Response<JsonModel>{
        return apiService.getData(id)
    }
}