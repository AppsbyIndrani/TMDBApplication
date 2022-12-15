package com.example.tmdbapplication.network

import com.example.tmdbapplication.model.MovieCredits
import com.example.tmdbapplication.model.MovieData
import com.example.tmdbapplication.model.TrendingMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi
{

    @GET("trending/movie/week?")
     fun getTrendingMovies(
        @Query("api_key")
        key:String
    ): Call<TrendingMovies>

     @GET("movie/{movie_id}?language=en-US")
     fun getMovie(
         @Path("movie_id")movieId:Int,
         @Query("api_key")key: String
     ):Call<MovieData>

     @GET("movie/{movie_id}/credits?&language=en-US")
     fun getMovieCast(
         @Path("movie_id")movieId: Int,
         @Query("api_key")key: String
     ):Call<MovieCredits>


}