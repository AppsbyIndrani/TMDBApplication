package com.example.tmdbapplication.repository

import com.example.tmdbapplication.db.MovieDatabase
import com.example.tmdbapplication.network.MovieApiService

class MovieRepository()
{

    fun getAllMoviesList(key:String)=
        MovieApiService.getMovieApi().getTrendingMovies(key)

    fun getMovieDetails(id:Int,key:String)=
        MovieApiService.getMovieApi().getMovie(id,key)

    fun getMovieCredits(id:Int,key:String)=
        MovieApiService.getMovieApi().getMovieCast(id,key)




    //private val movieapiservice:MovieApiService


}