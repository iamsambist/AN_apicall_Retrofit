package com.sunaa.apifetch.fetchdata.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getData(): Response<List<JsonModel>>

    @GET("posts/{id}")
    suspend fun getData(@Path("id") id : Int) : Response<JsonModel>

    // @GET("posts/{id}"): This allows the API call to dynamically include the id in the URL.
    // @Path("id") id: Int: This annotation tells Retrofit to replace the {id} placeholder
    // in the URL with the actual value passed to the function.
}