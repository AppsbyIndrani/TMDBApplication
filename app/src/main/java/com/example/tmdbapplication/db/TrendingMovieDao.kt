package com.example.tmdbapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdbapplication.model.MovieList
import com.example.tmdbapplication.model.TrendingMovies


@Dao
interface TrendingMovieDao
{
     @Insert
     suspend fun addMovies(movielist: List<MovieList>)

    @Query("SELECT * FROM trendingmovie")
     suspend fun getMovies():List<MovieList>

}