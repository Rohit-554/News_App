package com.example.newsapp_train

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=137d9a7a5a9947ebbed7ffe5a9c73e24
    suspend fun getNews(@Query("country") country:String,@Query("apiKey") apiKey:String): Response<NewsDTO>



}