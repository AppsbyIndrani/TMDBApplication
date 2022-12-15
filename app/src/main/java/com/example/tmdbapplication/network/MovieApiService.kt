package com.example.tmdbapplication.network

import com.example.tmdbapplication.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiService
{
    private val retrofit=
        Retrofit.Builder().baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private val movieApi:MovieApi=
        retrofit.create(MovieApi::class.java)


    fun getMovieApi() : MovieApi
    {
        return movieApi
    }
}